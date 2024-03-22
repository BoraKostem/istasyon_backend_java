package com.istasyon.backend.controllers.company;

import com.istasyon.backend.entities.CompPostsAds;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.ApplicationRepo;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/applications")
public class CompanyDashboardController {
    private final JsonCreator jsonCreator;
    private final ApplicationRepo applicationRepo;
    private final CompanyRepo companyRepo;
    private final JobAddRepo jobAddRepo;
    public CompanyDashboardController(JsonCreator jsonCreator, ApplicationRepo applicationRepo, CompanyRepo companyRepo, JobAddRepo jobAddRepo) {
        this.jsonCreator = jsonCreator;
        this.applicationRepo = applicationRepo;
        this.companyRepo = companyRepo;
        this.jobAddRepo = jobAddRepo;
    }

    @GetMapping("/get")
    public ResponseEntity<CustomJson<Object>> getApplications(@RequestParam(required = false) Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(currentUser.getUserId());
        Map<String, Object> response = new HashMap<>();
        if(id == null) {
           response.put("applications", applicationRepo.findByCompany(company));
        }
        else{
            CompPostsAds add = jobAddRepo.findByadId(id);
            if(add == null || !add.getCompany().equals(company)){
                response.put("Message", "Invalid advertisement id");
                return jsonCreator.create(response, 400);
            }
            response.put("applications", applicationRepo.findByCompanyAndCompPostsAds(company, add));
        }
        return jsonCreator.create(response, 200);
    }

}
