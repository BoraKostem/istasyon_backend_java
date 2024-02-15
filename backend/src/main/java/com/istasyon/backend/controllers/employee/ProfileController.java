package com.istasyon.backend.controllers.employee;

import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/profile")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class ProfileController {
    private final JsonCreator jsonCreator;
    public ProfileController(JsonCreator jsonCreator) {
        this.jsonCreator = jsonCreator;
    }
}
