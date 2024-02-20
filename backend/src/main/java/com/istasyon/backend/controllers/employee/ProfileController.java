package com.istasyon.backend.controllers.employee;

import com.istasyon.backend.dataObjects.EmployeeProfileDTO;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.EmployeeProfile;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeeProfileRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.services.AWSS3Service;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user/profile")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class ProfileController {
    private final JsonCreator jsonCreator;
    private final EmployeeProfileRepo employeeProfileRepo;
    private final AWSS3Service awss3Service;
    public ProfileController(JsonCreator jsonCreator, EmployeeProfileRepo employeeProfileRepo, AWSS3Service awss3Service) {
        this.jsonCreator = jsonCreator;
        this.employeeProfileRepo = employeeProfileRepo;
        this.awss3Service = awss3Service;
    }

    @PostMapping("/edit")
    public ResponseEntity<CustomJson<Object>> editProfile(@ModelAttribute EmployeeProfileDTO employeeProfileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        EmployeeProfile employeeProfile = employeeProfileRepo.findByid(currentUser.getUserId());
        HashMap<String, Object> response = new HashMap<>();
        response.put("Message", "Profile edited successfully");
        response.put("User", currentUser.getUserId());
        if (employeeProfileDTO.getProfilePhoto() != null) {
            if("image/jpeg".equals(employeeProfileDTO.getProfilePhoto().getContentType()) || "image/png".equals(employeeProfileDTO.getProfilePhoto().getContentType())){
                try {
                    String url = awss3Service.uploadFile(employeeProfileDTO.getProfilePhoto(),"profile-photos/");
                    employeeProfile.setProfilePhotoUrl(url);
                    response.put("profilePhotoUrl", url);
                } catch (Exception e) {
                    return jsonCreator.create("Profile photo upload failed! Reason: " + e, 500);
                }
            }
            else{
                return jsonCreator.create("Profile photo upload failed! Reason: File type not supported", 500);
            }

        }
        if (employeeProfileDTO.getBackgroundPhoto() != null) {
            if("image/jpeg".equals(employeeProfileDTO.getBackgroundPhoto().getContentType()) || "image/png".equals(employeeProfileDTO.getBackgroundPhoto().getContentType())){
                try {
                    String url = awss3Service.uploadFile(employeeProfileDTO.getBackgroundPhoto(),"background-photos/");
                    employeeProfile.setBackgroundPhotoUrl(url);
                    response.put("backgroundPhotoUrl", url);
                } catch (Exception e) {
                    return jsonCreator.create("Background photo upload failed! Reason: " + e, 500);
                }
            }
            else{
                return jsonCreator.create("Background photo upload failed! Reason: File type not supported", 500);
            }

        }
        if (employeeProfileDTO.getAboutMe() != null) {
            employeeProfile.setAboutMe(employeeProfileDTO.getAboutMe());
            response.put("aboutMe", employeeProfileDTO.getAboutMe());
        }
        if (employeeProfileDTO.getJobTitle() != null) {
            employeeProfile.setJobTitle(employeeProfileDTO.getJobTitle());
            response.put("jobTitle", employeeProfileDTO.getJobTitle());
        }
        if (employeeProfileDTO.getDepartment() != null) {
            employeeProfile.setDepartment(employeeProfileDTO.getDepartment());
            response.put("department", employeeProfileDTO.getDepartment());
        }
        if (employeeProfileDTO.getResume() != null) {
            if("application/pdf".equals(employeeProfileDTO.getResume().getContentType())){
                try {
                    String url = awss3Service.uploadFile(employeeProfileDTO.getResume(),"resumes/");
                    employeeProfile.setResumeUrl(url);
                    response.put("resumeUrl", url);
                } catch (Exception e) {
                    return jsonCreator.create("Resume upload failed! Reason: " + e, 500);
                }
            }
            else {
                return jsonCreator.create("Resume upload failed! Reason: File type not supported", 500);
            }

        }
        try {
            employeeProfileRepo.save(employeeProfile);
        } catch (Exception e) {
            return jsonCreator.create("Employee profile edit failed! Reason: " + e, 500);
        }

        return jsonCreator.create(response, 200);
    }

    @GetMapping("")
    public ResponseEntity<CustomJson<Object>> getProfile() {
        EmployeeProfile employeeProfile;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User currentUser = (User) authentication.getPrincipal();
            employeeProfile = employeeProfileRepo.findByid(currentUser.getUserId());
        } catch (Exception e) {
            return jsonCreator.create("Error during profile retrieval: " + e, 500);
        }
        return jsonCreator.create(employeeProfile, 200);
    }

    //Todo: Add a method to convert image to AWS S3 URL

}
