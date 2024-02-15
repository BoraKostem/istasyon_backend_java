package com.istasyon.backend.dataObjects;

import com.istasyon.backend.entities.Employee;

public class EmployeeProfileDTO {
    private String profilePhotoUrl;
    private String backgroundPhotoUrl;
    private String aboutMe;
    private String jobTitle;
    private String department;
    private String resumeUrl;

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getBackgroundPhotoUrl() {
        return backgroundPhotoUrl;
    }

    public void setBackgroundPhotoUrl(String backgroundPhotoUrl) {
        this.backgroundPhotoUrl = backgroundPhotoUrl;
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

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
}
