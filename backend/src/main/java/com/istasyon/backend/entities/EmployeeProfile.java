package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "EmployeeProfile")
public class EmployeeProfile {
    @Id
    private Long id;
    @Column
    private String profilePhotoUrl;
    @Column
    private String backgroundPhotoUrl;
    @Column
    private String aboutMe;
    @Column
    private String jobTitle;
    @Column
    private String department;
    @Column
    private String resumeUrl;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "eUserNo", insertable = false, updatable = false)
    private Employee employee;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
