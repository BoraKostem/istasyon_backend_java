package com.istasyon.backend.controllers.company;

import com.istasyon.backend.controllers.ProfileController;
import com.istasyon.backend.entities.Application;
import com.istasyon.backend.entities.CompPostsAds;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.ApplicationRepo;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.repositories.compositeIds.ApplicationId;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProfileController globalProfileController;
    public CompanyDashboardController(JsonCreator jsonCreator, ApplicationRepo applicationRepo, CompanyRepo companyRepo, JobAddRepo jobAddRepo) {
        this.jsonCreator = jsonCreator;
        this.applicationRepo = applicationRepo;
        this.companyRepo = companyRepo;
        this.jobAddRepo = jobAddRepo;
    }

    /**
     * This method is used to get the applications for a company.
     * It is mapped to the "/get" endpoint and responds to HTTP GET requests.
     *
     * @param adId The ID of the job advertisement. This is an optional request parameter.
     * @return ResponseEntity<CustomJson<Object>> This returns a JSON response with the applications if successful, or an error message if not.
     */
    @GetMapping("/get")
    public ResponseEntity<CustomJson<Object>> getApplications(@RequestParam(required = false) Long adId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(currentUser.getUserId());
        Map<String, Object> response = new HashMap<>();
        if(adId == null) {
           response.put("applications", applicationRepo.findByCompany(company));
        }
        else{
            CompPostsAds add = jobAddRepo.findByadId(adId);
            if(add == null || !add.getCompany().equals(company)){
                response.put("Message", "Invalid advertisement id");
                return jsonCreator.create(response, 400);
            }
            response.put("applications", applicationRepo.findByCompanyAndCompPostsAds(company, add));
        }
        return jsonCreator.create(response, 200);
    }

    /**
     * This method is used to get the details of an applicant for a specific job advertisement.
     * It is mapped to the "/applicant/get" endpoint and responds to HTTP GET requests.
     *
     * @param adId The ID of the job advertisement. This is a required request parameter.
     * @param applicantId The ID of the applicant. This is a required request parameter.
     * @return ResponseEntity<CustomJson<Object>> This returns a JSON response with the applicant's details if successful, or an error message if not.
     */
    @GetMapping("/applicant/get")
    public ResponseEntity<CustomJson<Object>> getApplicant(@RequestParam Long adId, @RequestParam Long applicantId) {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(currentUser.getUserId());
        CompPostsAds add = jobAddRepo.findByadId(adId);
        if(add == null || !add.getCompany().equals(company)){
            response.put("Message", "Invalid advertisement id");
            return jsonCreator.create(response, 400);
        }
        ApplicationId applicationId = new ApplicationId(company.getcUserNo(),add.getAdId(), applicantId);
        Application application = applicationRepo.findById(applicationId).orElse(null);
        if(application == null){
            response.put("Message", "Applicant not found");
            return jsonCreator.create(response, 400);
        }
        response.put("Applicant", globalProfileController.getEmployeeProfile(applicantId));
        return jsonCreator.create(response, 200);
    }

}
