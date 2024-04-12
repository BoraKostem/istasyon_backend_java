package com.istasyon.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istasyon.backend.dataObjects.UserDTO;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.security.CustomUserDetails;
import com.istasyon.backend.security.JwtTokenFilter;
import com.istasyon.backend.security.UserAuthProvider;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import com.istasyon.backend.utilities.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class LoginController {
    private final UserRepo userRepository;
    private final JsonCreator jsonCreator;
    private final TokenUtil tokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserAuthProvider userAuthProvider;
    public LoginController(UserRepo userRepository, JsonCreator jsonCreator, TokenUtil tokenUtil, UserAuthProvider userAuthProvider) {
        this.userRepository = userRepository;
        this.jsonCreator = jsonCreator;
        this.tokenUtil = tokenUtil;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<CustomJson<Object>> login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        try {
            if (userDTO.getEmail() == null || userDTO.getPassword() == null) {
                return jsonCreator.create("Email and password must be provided", 400);
            }
            Authentication auth = userAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
            if (auth.isAuthenticated()) {
                CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
                logger.info("User logged in: " + user.getUsername());
                Cookie cookie = tokenUtil.encodeCookie(user);
                response.addCookie(cookie);
                return jsonCreator.create(auth.getPrincipal(), 200);
            } else {
                return jsonCreator.create("Login failed", 401);
            }
        } catch (Exception e) {
            return jsonCreator.create("Error parsing user data: " + e, 500);
        }
    }
}