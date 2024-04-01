package com.istasyon.backend.controllers;
import com.istasyon.backend.entities.Employee;
import com.istasyon.backend.entities.EmployeePrevJobs;
import com.istasyon.backend.entities.EmployeeProfile;
import com.istasyon.backend.repositories.EmployeePrevJobsRepo;
import com.istasyon.backend.repositories.EmployeeProfileRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ProfileController(JsonCreator jsonCreator, EmployeeRepo employeeRepo, EmployeeProfileRepo employeeProfileRepo, EmployeePrevJobsRepo employeePrevJobsRepo) {
        this.jsonCreator = jsonCreator;
        this.employeeRepo = employeeRepo;
        this.employeeProfileRepo = employeeProfileRepo;
        this.employeePrevJobsRepo = employeePrevJobsRepo;
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
}
