package com.istasyon.backend.controllers.employee;

import com.istasyon.backend.dataObjects.ApplicationDTO;
import com.istasyon.backend.entities.*;
import com.istasyon.backend.repositories.*;
import com.istasyon.backend.repositories.compositeIds.ApplicationId;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import com.istasyon.backend.utilities.CustomJson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.istasyon.backend.entities.enumeration.Status.ACTIVE;

@RestController
@RequestMapping("/user/job")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class JobApplicationController {
    private final JsonCreator jsonCreator;
    private final EmployeeRepo employeeRepo;
    private final ApplicationRepo applicationRepo;
    private final JobAddRepo jobAddRepo;
    private final CompanyRepo companyRepo;
    public JobApplicationController(JsonCreator jsonCreator, EmployeeRepo employeeRepo, ApplicationRepo applicationRepo, JobAddRepo jobAddRepo, CompanyRepo companyRepo) {
        this.jsonCreator = jsonCreator;
        this.employeeRepo = employeeRepo;
        this.applicationRepo = applicationRepo;
        this.jobAddRepo = jobAddRepo;
        this.companyRepo = companyRepo;
    }

    /**
     * This method is responsible for applying for a job advertisement.
     * It first retrieves the current authenticated user and finds the associated employee.
     * Then, it creates a new application using the provided DTO and the employee.
     * If the creation is successful, it saves the application to the repository.
     * If an exception occurs during the creation, it returns a response with a 500 status code and a failure message.
     * If the creation is successful, it returns a response with a 200 status code and a success message.
     *
     * @param applicationDTO The data transfer object containing the details of the application to be created.
     * @return A ResponseEntity containing a CustomJson object. The object contains a message indicating the result of the operation and, in case of success, the created application.
     * @throws Exception If an error occurs during the creation of the application.
     */
    @PostMapping("/apply")
    public ResponseEntity<CustomJson<Object>> applyForJob(@RequestBody ApplicationDTO applicationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        HashMap<String, Object> response = new HashMap<>();
        if(applicationDTO.getCompanyId() == (long)-1 || applicationDTO.getPostId() == (long)-1) {
            response.put("Message", "Invalid company or post id");
            return jsonCreator.create(response, 400);
        }
        CompPostsAds jobAdd = jobAddRepo.findByadId(applicationDTO.getPostId());
        if(jobAdd == null || jobAdd.getStatus() != ACTIVE) {
            response.put("Message", "Job advertisement not found");
            return jsonCreator.create(response, 404);
        }
        ApplicationId applicationId = new ApplicationId(applicationDTO.getCompanyId(), applicationDTO.getPostId(), currentUser.getUserId());
        if(applicationRepo.findById(applicationId).isPresent()) {
            response.put("Message", "You have already applied for this job");
            return jsonCreator.create(response, 400);
        }
        Application application = getApplication(applicationDTO, currentUser, jobAdd);
        try {
            applicationRepo.save(application);
            response.put("Message", "Job application submitted successfully");
            response.put("Application", application.getCompPostsAds());
        } catch (Exception e) {
            response.put("Message", "Error occurred while submitting job application");
            return jsonCreator.create(response, 500);
        }

        return jsonCreator.create(response, 200);
    }

    /**
     * This method is responsible for creating a new application from an ApplicationDTO and an Employee.
     * It creates a new application and sets its properties using the values from the DTO.
     * It also sets the employee of the application.
     *
     * @param applicationDTO The data transfer object containing the details of the application to be created.
     * @param employee The employee associated with the application.
     * @param jobAdd The job advertisement associated with the application.
     * @return The created application.
     */
    private Application getApplication(ApplicationDTO applicationDTO, User employee, CompPostsAds jobAdd) {
        Application application = new Application();
        application.setCompany(companyRepo.findBycUserNo(applicationDTO.getCompanyId()));
        application.setCompPostsAds(jobAdd);
        application.setEmployee(employeeRepo.findByeUserNo(employee.getUserId()));
        application.setLike_col(applicationDTO.getLikeOffer());
        application.setDecision(applicationDTO.getDecision());
        return application;
    }

    @GetMapping("/isQualified")
    public ResponseEntity<CustomJson<Object>> isQualified(@RequestParam long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        HashMap<String, Object> response = new HashMap<>();
        CompPostsAds jobAdd = jobAddRepo.findByadId(postId);
        if(jobAdd == null || jobAdd.getStatus() != ACTIVE) {
            response.put("Message", "Job advertisement not found");
            return jsonCreator.create(response, 404);
        }
        /*
        if(jobAdd.getQualification() == null || jobAdd.getQualification().isEmpty()) {
            response.put("Message", "No qualification required");
            return jsonCreator.create(response, 200);
        }
        Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
        if(employee.getQualification() == null || employee.getQualification().isEmpty()) {
            response.put("Message", "You are not qualified for this job");
            return jsonCreator.create(response, 200);
        }
        String[] jobQualifications = jobAdd.getQualification().split(",");
        String[] employeeQualifications = employee.getQualification().split(",");
        for(String jobQualification : jobQualifications) {
            boolean found = false;
            for(String employeeQualification : employeeQualifications) {
                if(jobQualification.equals(employeeQualification)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                response.put("Message", "You are not qualified for this job");
                return jsonCreator.create(response, 200);
            }
        }

         */
        response.put("Message", "You are qualified for this job");
        return jsonCreator.create(response, 200);
    }
}
