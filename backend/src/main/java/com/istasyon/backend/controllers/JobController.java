package com.istasyon.backend.controllers;

import com.istasyon.backend.entities.CompPostsAds;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.Employee;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.services.GoogleMapsService;
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

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JsonCreator jsonCreator;
    private final JobAddRepo jobAddRepo;
    private final EmployeeRepo employeeRepo;
    private final GoogleMapsService googleMapsService;
    public JobController(JsonCreator jsonCreator, JobAddRepo jobAddRepo, EmployeeRepo employeeRepo, GoogleMapsService googleMapsService) {
        this.jsonCreator = jsonCreator;
        this.jobAddRepo = jobAddRepo;
        this.employeeRepo = employeeRepo;
        this.googleMapsService = googleMapsService;
    }
    @GetMapping("/view")
    public ResponseEntity<CustomJson<Object>> viewJob(@RequestParam(required = false) Long id) {
        if(id == null){
            return jsonCreator.create("What do you expect to view ≽^•⩊•^≼", 418);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        HashMap<String, Object> response = new HashMap<>();
        CompPostsAds job = jobAddRepo.findByadId(id);
        if(job == null) {
            response.put("Message", "Job not found");
            return jsonCreator.create(response, 404);
        }
        response.put("Job", job);
        Company company = job.getCompany();
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
            Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
            response.put("Distance", googleMapsService.computeDistance(employee, company));
            job.setViewCount(job.getViewCount() + 1);
            jobAddRepo.save(job);
        }

        return jsonCreator.create(response, 200);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<CustomJson<Object>> searchJobs(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer offset) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        HashMap<String, Object> response = new HashMap<>();
        if(size == null || size > 50){
            size = 10;
        }
        if(offset == null){
            offset = 0;
        }
        //Add AI job recommendation here

        return jsonCreator.create(response, 200);
    }
}
