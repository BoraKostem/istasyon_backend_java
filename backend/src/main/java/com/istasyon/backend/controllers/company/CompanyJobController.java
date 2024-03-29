package com.istasyon.backend.controllers.company;

import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/company/job")
public class CompanyJobController {
    private final JsonCreator jsonCreator;

    public CompanyJobController(JsonCreator jsonCreator) {
        this.jsonCreator = jsonCreator;
    }
}
