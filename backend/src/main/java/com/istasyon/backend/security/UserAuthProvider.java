package com.istasyon.backend.security;

import com.istasyon.backend.controllers.LoginController;
import com.istasyon.backend.services.UserService;
import com.istasyon.backend.utilities.TokenUtil;
import io.netty.handler.codec.http.cookie.Cookie;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthProvider.class);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        logger.info("User details: " + userDetails.getUsername());
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Password is incorrect");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
