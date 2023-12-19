package com.istasyon.backend.controllers;

import com.istasyon.backend.dataObjects.UserDTO;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import com.istasyon.backend.utilities.TokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class LoginController {
    private final UserRepo userRepository;
    private final JsonCreator jsonCreator;
    private final TokenUtil tokenUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    public LoginController(UserRepo userRepository, JsonCreator jsonCreator, TokenUtil tokenUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jsonCreator = jsonCreator;
        this.tokenUtil = tokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public ResponseEntity<CustomJson<String>> login(HttpServletRequest request) {
        String data = "Login successful";
        return jsonCreator.create(data);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomJson<String>> login(@ModelAttribute UserDTO userDTO, HttpServletResponse response) {
        try {
            if (userDTO.getEmail() == null || userDTO.getPassword() == null) {
                return jsonCreator.create("Email and password must be provided", 400);
            }
            User userInDb = userRepository.findByEmail(userDTO.getEmail());
            if (userInDb != null && passwordEncoder.matches(userDTO.getPassword(), userInDb.getPassword())) {
                Cookie cookie = tokenUtil.encodeCookie(userInDb);
                response.addCookie(cookie);

                return jsonCreator.create("Login successful");
            } else {
                return jsonCreator.create("Invalid email or password", 403);
            }
        } catch (Exception e) {
            return jsonCreator.create("Error parsing user data: " + e, 500);
        }
    }
}