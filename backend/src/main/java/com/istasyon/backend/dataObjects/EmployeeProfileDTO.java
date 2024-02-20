package com.istasyon.backend.dataObjects;

import com.istasyon.backend.entities.Employee;
import org.springframework.web.multipart.MultipartFile;

public class EmployeeProfileDTO {
    private MultipartFile profilePhoto = null;
    private MultipartFile backgroundPhoto = null;
    private String aboutMe = null;
    private String jobTitle = null;
    private String department = null;
    private MultipartFile resume = null;

    public MultipartFile getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(MultipartFile profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public MultipartFile getBackgroundPhoto() {
        return backgroundPhoto;
    }

    public void setBackgroundPhoto(MultipartFile backgroundPhoto) {
        this.backgroundPhoto = backgroundPhoto;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public MultipartFile getResume() {
        return resume;
    }

    public void setResume(MultipartFile resume) {
        this.resume = resume;
    }
}
