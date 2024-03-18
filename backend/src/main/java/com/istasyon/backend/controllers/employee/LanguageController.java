package com.istasyon.backend.controllers.employee;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/language")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class LanguageController {
    @PostMapping("/add")
    public void addLanguage() {
        // Add language to the employee's profile
    }
}
