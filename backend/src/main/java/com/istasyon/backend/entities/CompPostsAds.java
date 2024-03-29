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
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adId;

    @Column(name = "job_name", length = 50)
    private String jobName;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "estimated_salary")
    private Integer estimatedSalary;
    @Enumerated(EnumType.STRING) // "TL", "USD", "EUR"
    private Currency currency;
    @Enumerated(EnumType.STRING) // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private JobType jobType;
    @Column(name = "food", columnDefinition = "TEXT")
    private String food;
    @Column(name = "bonus")
    private Boolean bonus;
    @Column(name = "health_insurance", columnDefinition = "TEXT")
    private String healthInsurance;
    @Column(name = "insurance", columnDefinition = "TEXT")
    private String insurance;
    @Column(name = "exp_min")
    private Integer expMin;
    @Column(name = "edu_min", columnDefinition = "TEXT")
    private String eduMin;
    @Column(length = 1)
    private String gender;
    @Enumerated(EnumType.STRING) // "ACTIVE", "DRAFT", "DELETED
    private Status status;
    @Column(name = "work_days")
    private int workDays;
    @Column(name = "work_hours", columnDefinition = "REAL")
    private Double workHours;
    @Column(name = "view_count")
    private Integer viewCount = 0;
    @Enumerated(EnumType.STRING) // "TRANSPORTATION_BUDGET", "COMPANY_SERVICE", "NULL"
    private Transportation transportation;
    @Column(name = "skills")
    private String skills;
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

    public int getWorkDays() {
        return workDays;
    }

    public void setWorkDays(int workDays) {
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

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Integer getExpMin() {
        return expMin;
    }

    public void setExpMin(Integer expMin) {
        this.expMin = expMin;
    }

    public String getEduMin() {
        return eduMin;
    }

    public void setEduMin(String eduMin) {
        this.eduMin = eduMin;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}

