package com.istasyon.backend.security;

import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    @Value("${custom.cookieName}")
    private String cookieName;

    public JwtTokenFilter(TokenUtil jwtTokenUtil,UserRepo userRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo= userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // Get cookie and validate
//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        var cookie = this.readCookieFromRequest(request);

        if (cookie.isEmpty() || !cookie.get().getName().equals(cookieName)) {
            chain.doFilter(request, response);
            return;
        }

//        if (isEmpty(header) || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }

        // Get jwt token and validate
//        final String token = header.split(" ")[1].trim();
//        if (!jwtTokenUtil.validate(token)) {
//            chain.doFilter(request, response);
//            return;
//        }

        final String token = cookie.get().getValue();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        User userDetails = userRepo
                .findByEmail(jwtTokenUtil.getEmail(token));

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,null);

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Optional<jakarta.servlet.http.Cookie> readCookieFromRequest(HttpServletRequest request) {
        var cook = request.getCookies();

        if (cook == null) {
            return Optional.empty();
        }

        Optional<Cookie> maybeCookie = Stream.of(request.getCookies())
                .filter(c -> cookieName.equals(c.getName()))
                .findFirst();

        return maybeCookie;
    }
}
