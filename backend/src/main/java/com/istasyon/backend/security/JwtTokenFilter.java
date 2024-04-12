package com.istasyon.backend.security;

import com.istasyon.backend.entities.User;
import com.istasyon.backend.services.UserService;
import com.istasyon.backend.utilities.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenUtil tokenUtil;
    private final UserService userService;
    @Value("${custom.cookieName}")
    private String cookieName;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String jwt;
        var cookie = this.readCookieFromRequest(request);

        if (cookie.isEmpty() || !cookie.get().getName().equals(cookieName)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = cookie.get().getValue();
        String email = tokenUtil.getEmail(jwt);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            CustomUserDetails userDetails = userService.loadUserByUsername(email);
            if(tokenUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                logger.error("Token is valid" + authToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
            }
            else{
                logger.error("Token is not valid");
            }
        }
        filterChain.doFilter(request, response);
    }

    public Optional<Cookie> readCookieFromRequest(HttpServletRequest request) {
        var cook = request.getCookies();
        if (cook == null) {
            return Optional.empty();
        }
        return Stream.of(request.getCookies())
                .filter(c -> cookieName.equals(c.getName()))
                .findFirst();
    }
}
