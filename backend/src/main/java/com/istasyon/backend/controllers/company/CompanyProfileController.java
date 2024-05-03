package com.istasyon.backend.controllers.company;

import com.istasyon.backend.dataObjects.CompanyProfileDTO;
import com.istasyon.backend.entities.CompanyProfile;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyProfileRepo;
import com.istasyon.backend.services.AWSS3Service;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/company/profile")
public class CompanyProfileController{
    private final JsonCreator jsonCreator;
    private final CompanyProfileRepo companyProfileRepo;
    private final AWSS3Service awss3Service;
    public CompanyProfileController(JsonCreator jsonCreator, CompanyProfileRepo companyProfileRepo, AWSS3Service awss3Service) {
        this.jsonCreator = jsonCreator;
        this.companyProfileRepo = companyProfileRepo;
        this.awss3Service = awss3Service;
    }
    @PostMapping("/edit")
    public ResponseEntity<CustomJson<Object>> editProfile(@ModelAttribute CompanyProfileDTO companyProfileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        CompanyProfile companyProfile = companyProfileRepo.findByid(currentUser.getUserId());
        HashMap<String, Object> response = new HashMap<>();
        response.put("Message", "Profile edited successfully");
        response.put("Company", currentUser.getUserId());
        if (companyProfileDTO.getProfilePhoto() != null) {
            if("image/jpeg".equals(companyProfileDTO.getProfilePhoto().getContentType()) || "image/png".equals(companyProfileDTO.getProfilePhoto().getContentType())){
                try {
                    String url = awss3Service.uploadFile(companyProfileDTO.getProfilePhoto(),"profile-photos/");
                    companyProfile.setProfilePhotoUrl(url);
                    response.put("profilePhotoUrl", url);
                } catch (Exception e) {
                    return jsonCreator.create("Profile photo upload failed! Reason: " + e, 500);
                }
            }
            else{
                return jsonCreator.create("Profile photo upload failed! Reason: File type not supported", 500);
            }

        }
        if (companyProfileDTO.getAboutMe() != null) {
            companyProfile.setAboutMe(companyProfileDTO.getAboutMe());
            response.put("aboutMe", companyProfileDTO.getAboutMe());
        }
        if (companyProfileDTO.getCompanyIndustry() != null) {
            companyProfile.setCompanyIndustry(companyProfileDTO.getCompanyIndustry());
            response.put("industry", companyProfileDTO.getCompanyIndustry());
        }
        try {
            companyProfileRepo.save(companyProfile);
        } catch (Exception e) {
            return jsonCreator.create("Company profile edit failed! Reason: " + e, 500);
        }

        return jsonCreator.create(response, 200);
    }
}
