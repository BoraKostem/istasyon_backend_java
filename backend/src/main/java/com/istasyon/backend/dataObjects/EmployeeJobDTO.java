package com.istasyon.backend.dataObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.enumeration.JobType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class EmployeeJobDTO {
    private Long companyId;
    private String companyName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate = LocalDate.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate = LocalDate.now();
    @Column
    @Enumerated(EnumType.STRING) // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private JobType jobType = JobType.FULL_TIME;
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
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
}
