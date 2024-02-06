package com.istasyon.backend.entities;

import com.istasyon.backend.entities.enumeration.Currency;
import com.istasyon.backend.entities.enumeration.JobType;
import com.istasyon.backend.entities.enumeration.Status;
import com.istasyon.backend.entities.enumeration.Transportation;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Comp_Posts_Ads")
public class CompPostsAds {

    @Id
    @Column(length = 8)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adId;

    @Column(length = 50)
    private String jobName;

    private LocalDate publishDate;
    private Integer estimatedSalary;
    @Enumerated(EnumType.STRING) // "TL", "USD", "EUR"
    private Currency currency;

    @Column(length = 50)
    private String workDays;

    private Double workHours;

    @Enumerated(EnumType.STRING) // "TRANSPORTATION_BUDGET", "COMPANY_SERVICE", "NULL"
    private Transportation transportation;

    @Enumerated(EnumType.STRING) // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private JobType jobType;

    private Integer ageMin;
    private Integer ageMax;

    @Column(length = 1)
    private String gender;

    @Enumerated(EnumType.STRING) // "ACTIVE", "DRAFT", "DELETED
    private Status status;

    @ManyToOne
    @JoinColumn(name = "c_user_no")
    private Company company;

    // Getters and setters...

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getEstimatedSalary() {
        return estimatedSalary;
    }

    public void setEstimatedSalary(Integer estimatedSalary) {
        this.estimatedSalary = estimatedSalary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public Double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Double workHours) {
        this.workHours = workHours;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

