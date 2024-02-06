package com.istasyon.backend.controllers.company;

import com.istasyon.backend.dataObjects.JobAddDTO;
import com.istasyon.backend.entities.CompPostsAds;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class JobAddsController {
    private final JobAddRepo jobAddRepo;
    private final JsonCreator jsonCreator;
    private final CompanyRepo companyRepo;
    public JobAddsController(JobAddRepo jobAddRepo, JsonCreator jsonCreator, CompanyRepo companyRepo) {
        this.jobAddRepo = jobAddRepo;
        this.jsonCreator = jsonCreator;
        this.companyRepo = companyRepo;
    }

    @PostMapping("/company/jobAdd/create")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<CustomJson<Object>> createJobAdd(@ModelAttribute JobAddDTO jobAddDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(currentUser.getUserId());
        try {
            CompPostsAds jobAdd = getJobAdd(jobAddDTO, company);
            jobAddRepo.save(jobAdd);
        } catch (Exception e) {
            return jsonCreator.create(new HashMap<String,Object>(){{
                put("Message","Job Add Creation Failed: " + e);
            }}, 500);
        }
        return jsonCreator.create(new HashMap<String,Object>(){{
            put("Message","Job Add Created Successfully");
            put("Item",jobAddDTO);
        }}, 200);
    }

    private CompPostsAds getJobAdd(JobAddDTO jobAddDTO, Company company) {
        CompPostsAds jobAdd = new CompPostsAds();
        jobAdd.setJobName(jobAddDTO.getJobName());
        jobAdd.setJobType(jobAddDTO.getJobType());
        jobAdd.setPublishDate(jobAddDTO.getPublishDate());
        jobAdd.setEstimatedSalary(jobAddDTO.getEstimatedSalary());
        jobAdd.setCurrency(jobAddDTO.getCurrency());
        jobAdd.setWorkDays(jobAddDTO.getWorkDays());
        jobAdd.setWorkHours(jobAddDTO.getWorkHours());
        jobAdd.setTransportation(jobAddDTO.getTransportation());
        jobAdd.setAgeMin(jobAddDTO.getAgeMin());
        jobAdd.setAgeMax(jobAddDTO.getAgeMax());
        jobAdd.setGender(jobAddDTO.getGender());
        jobAdd.setStatus(jobAddDTO.getStatus());
        jobAdd.setCompany(company);
        return jobAdd;
    }
}
