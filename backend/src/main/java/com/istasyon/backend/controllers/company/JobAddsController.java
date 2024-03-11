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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/company/jobAdd")
public class JobAddsController {
    private final JobAddRepo jobAddRepo;
    private final JsonCreator jsonCreator;
    private final CompanyRepo companyRepo;
    public JobAddsController(JobAddRepo jobAddRepo, JsonCreator jsonCreator, CompanyRepo companyRepo) {
        this.jobAddRepo = jobAddRepo;
        this.jsonCreator = jsonCreator;
        this.companyRepo = companyRepo;
    }

    /**
     * This method is responsible for creating a new job advertisement.
     * It first retrieves the current authenticated user and finds the associated company.
     * Then, it creates a new job advertisement using the provided DTO and the company.
     * If the creation is successful, it saves the job advertisement to the repository.
     * If an exception occurs during the creation, it returns a response with a 500 status code and a failure message.
     * If the creation is successful, it returns a response with a 200 status code and a success message.
     *
     * @param jobAddDTO The data transfer object containing the details of the job advertisement to be created.
     * @return A ResponseEntity containing a CustomJson object. The object contains a message indicating the result of the operation and, in case of success, the created job advertisement.
     * @throws Exception If an error occurs during the creation of the job advertisement.
     */
    @PostMapping("/create")
    public ResponseEntity<CustomJson<Object>> createJobAdd(@RequestBody JobAddDTO jobAddDTO) {
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

    /**
     * This method is responsible for creating a new job advertisement from a JobAddDTO and a Company.
     * It creates a new job advertisement and sets its properties using the values from the DTO.
     * It also sets the company of the job advertisement.
     *
     * @param jobAddDTO The data transfer object containing the details of the job advertisement to be created.
     * @param company The company associated with the job advertisement.
     * @return The created job advertisement.
     */
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
        jobAdd.setGender(jobAddDTO.getGender());
        jobAdd.setStatus(jobAddDTO.getStatus());
        jobAdd.setCompany(company);
        return jobAdd;
    }
}
