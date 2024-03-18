package com.istasyon.backend.dataObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.istasyon.backend.entities.enumeration.JobType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmployeePrevJobsDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private JobType jobType; // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private String employerComment;
    private Double efficiency;
    private Double communication;
    private Double teamWork;
    private Double responsibility;
    private Double personalGrowth;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getEmployerComment() {
        return employerComment;
    }

    public void setEmployerComment(String employerComment) {
        this.employerComment = employerComment;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getCommunication() {
        return communication;
    }

    public void setCommunication(Double communication) {
        this.communication = communication;
    }

    public Double getTeamWork() {
        return teamWork;
    }

    public void setTeamWork(Double teamWork) {
        this.teamWork = teamWork;
    }

    public Double getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(Double responsibility) {
        this.responsibility = responsibility;
    }

    public Double getPersonalGrowth() {
        return personalGrowth;
    }

    public void setPersonalGrowth(Double personalGrowth) {
        this.personalGrowth = personalGrowth;
    }
}
