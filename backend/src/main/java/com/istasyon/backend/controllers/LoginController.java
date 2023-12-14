package com.istasyon.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class LoginController {
    private final UserRepo userRepository;
    private final JsonCreator jsonCreator;

    public LoginController(UserRepo userRepository, JsonCreator jsonCreator) {
        this.userRepository = userRepository;
        this.jsonCreator = jsonCreator;
    }

    @GetMapping("/login")
    public ResponseEntity<CustomJson<String>> login(HttpServletRequest request) {
        String data = "Login successful";
        return jsonCreator.create(data);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomJson<String>> login(MultipartHttpServletRequest request) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User userInDb = userRepository.findByEmail(email);
            if (userInDb != null && userInDb.getPassword().equals(password)) {
                return jsonCreator.create("Login successful");
            } else {
                return jsonCreator.create("Invalid email or password", 403);
            }
        } catch (Exception e) {
            return jsonCreator.create("Error parsing user data: " + e, 500);
        }
    }
}