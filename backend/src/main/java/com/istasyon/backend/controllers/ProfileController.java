package com.istasyon.backend.controllers;
import com.istasyon.backend.entities.*;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeePrevJobsRepo;
import com.istasyon.backend.repositories.EmployeeProfileRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("globalProfileController")
@RequestMapping("/profile")
public class ProfileController {
    private final JsonCreator jsonCreator;
    private final EmployeeRepo employeeRepo;
    private final EmployeeProfileRepo employeeProfileRepo;
    private final EmployeePrevJobsRepo employeePrevJobsRepo;
    private final CompanyRepo companyRepo;
    public ProfileController(JsonCreator jsonCreator, EmployeeRepo employeeRepo, EmployeeProfileRepo employeeProfileRepo, EmployeePrevJobsRepo employeePrevJobsRepo, CompanyRepo companyRepo) {
        this.jsonCreator = jsonCreator;
        this.employeeRepo = employeeRepo;
        this.employeeProfileRepo = employeeProfileRepo;
        this.employeePrevJobsRepo = employeePrevJobsRepo;
        this.companyRepo = companyRepo;
    }

    public Map<String,Object> getEmployeeProfile(Long employeeId){
        Map<String,Object> response = new HashMap<>();
        Employee employee = employeeRepo.findByeUserNo(employeeId);
        if(employee == null){
            response.put("Employee",null);
            return response;
        }
        response.put("Employee",employee);

        //Employee Profile
        EmployeeProfile employeeProfile = employeeProfileRepo.findByid(employee.getEUserNo());
        Map<String,Object> profile = new HashMap<>();
        profile.put("AboutMe",employeeProfile.getAboutMe());
        profile.put("ProfilePhoto",employeeProfile.getProfilePhotoUrl());
        profile.put("BackgroundPhoto",employeeProfile.getBackgroundPhotoUrl());
        profile.put("Resume",employeeProfile.getResumeUrl());
        profile.put("Department",employeeProfile.getDepartment());
        profile.put("JobTitle",employeeProfile.getJobTitle());
        response.put("Profile",profile);

        //Employee Previous Jobs
        response.put("PreviousJobs",employeePrevJobsRepo.findByemployeeId(employee.getEUserNo()));

        return response;
    }

    public Map<String,Object> getCompanyProfile(Long companyId){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @GetMapping("")
    public ResponseEntity<CustomJson<Object>> getProfile(@RequestParam(required = false) Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(id == null){
            id = currentUser.getUserId();
            if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))){
                return jsonCreator.create(getEmployeeProfile(id));
            }
            else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"))){
                return jsonCreator.create(getCompanyProfile(id));
            }
        }
        Employee employee = employeeRepo.findByeUserNo(id);
        if(employee != null){
            return jsonCreator.create(getEmployeeProfile(id));
        }
        Company company = companyRepo.findBycUserNo(id);
        if(company != null){
            return jsonCreator.create(getCompanyProfile(id));
        }

        return jsonCreator.create("User not found",404);
    }
}
