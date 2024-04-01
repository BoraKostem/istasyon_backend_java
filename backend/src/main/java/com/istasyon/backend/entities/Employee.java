package com.istasyon.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.istasyon.backend.entities.enumeration.JobType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private Long eUserNo;

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNo;

    @Column(length = 20)
    private String phoneNo;

    @JsonIgnore
    @Column(length = 200)
    private String address;

    @JsonIgnore
    private Double xCoor;
    @JsonIgnore
    private Double yCoor;

    @JsonIgnore
    @Column(length = 6)
    private String confirmationCode;

    @JsonIgnore
    private LocalDateTime confirmationTime;

    @Column(length = 1)
    private String gender;

    private LocalDate birthDate;

    @JsonIgnore
    @Column(length = 10)
    private String militaryServiceInfo;

    @JsonIgnore
    private Boolean driversLicence;

    @JsonIgnore
    @Column(name = "edu_lvl", columnDefinition = "TEXT")
    private String eduLvl;

    @JsonIgnore
    @Column(name = "salary_expectation", columnDefinition = "TEXT")
    private String salaryExpectation;

    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "current_company", referencedColumnName = "cUserNo")
    private Company company;

    @JsonIgnore
    @Column(name = "company_name")
    private String companyName;
    @JsonIgnore
    @Column
    @Enumerated(EnumType.STRING) // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private JobType jobType;
    @JsonIgnore
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "skills")
    private String skills;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "eUserNo", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    // TO STRING
    @JsonProperty("name")
    public String getEmployeeName() {
        return user.getName();
    }

    @JsonProperty("surname")
    public String getEmployeeSurname() {
        return user.getSurname();
    }
    @Override
    public String toString() {
        return String.format(
                "[phoneNo='%s', gender='%s', birthDate='%s', name='%s', surname='%s']",
                phoneNo, gender, birthDate, (user != null ? user.getName() : "null"), (user != null ? user.getSurname() : "null"));
    }

    // Getters and setters...

    public Long getEUserNo() {
        return eUserNo;
    }

    public void setEUserNo(Long eUserNo) {
        this.eUserNo = eUserNo;
    }

    public Integer getIdNo() {
        return idNo;
    }

    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getxCoor() {
        return xCoor;
    }

    public void setxCoor(Double xCoor) {
        this.xCoor = xCoor;
    }

    public Double getyCoor() {
        return yCoor;
    }

    public void setyCoor(Double yCoor) {
        this.yCoor = yCoor;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public LocalDateTime getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMilitaryServiceInfo() {
        return militaryServiceInfo;
    }

    public void setMilitaryServiceInfo(String militaryServiceInfo) {
        this.militaryServiceInfo = militaryServiceInfo;
    }

    public Boolean getDriversLicence() {
        return driversLicence;
    }

    public void setDriversLicence(Boolean driversLicence) {
        this.driversLicence = driversLicence;
    }

    public String getEduLvl() {
        return eduLvl;
    }

    public void setEduLvl(String eduLvl) {
        this.eduLvl = eduLvl;
    }

    public String getSalaryExpectation() {
        return salaryExpectation;
    }

    public void setSalaryExpectation(String salaryExpectation) {
        this.salaryExpectation = salaryExpectation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
}
