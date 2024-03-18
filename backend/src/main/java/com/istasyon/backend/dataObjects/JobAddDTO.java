package com.istasyon.backend.dataObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.istasyon.backend.entities.enumeration.Currency;
import com.istasyon.backend.entities.enumeration.JobType;
import com.istasyon.backend.entities.enumeration.Status;
import com.istasyon.backend.entities.enumeration.Transportation;

import java.time.LocalDate;

public class JobAddDTO {
    private String jobName;
    private JobType jobType = JobType.FULL_TIME;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate = LocalDate.now();
    private Integer estimatedSalary;
    private Currency currency = Currency.TL;
    private int workDays;
    private Double workHours;
    private Transportation transportation = Transportation.NULL;
    private String gender = "";
    private Integer viewCount = 0;

    private Status status = Status.ACTIVE;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}