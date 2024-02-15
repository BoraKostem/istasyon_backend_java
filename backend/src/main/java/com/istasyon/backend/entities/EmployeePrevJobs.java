package com.istasyon.backend.entities;

import com.istasyon.backend.entities.enumeration.JobType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "EmployeePrevJobs")
public class EmployeePrevJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long employeeId;
    @Column
    private Long companyId;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    @Enumerated(EnumType.STRING) // "FULL_TIME", "PART_TIME", "INTERNSHIP", "TEMPORARY"
    private JobType jobType;
    @Column(length = 150)
    private String employerComment;
    @Column(columnDefinition = "DECIMAL(2,1) CHECK (efficiency >= 0 AND efficiency <= 5)") //This fill ensure that the efficiency is between 0 and 5 inclusive
    private Double efficiency;
    @Column(columnDefinition = "DECIMAL(2,1) CHECK (communication >= 0 AND communication <= 5)")
    private Double communication;
    @Column(columnDefinition = "DECIMAL(2,1) CHECK (team_work >= 0 AND team_work <= 5)")
    private Double team_work;
    @Column(columnDefinition = "DECIMAL(2,1) CHECK (responsibility >= 0 AND responsibility <= 5)")
    private Double responsibility;
    @Column(columnDefinition = "DECIMAL(2,1) CHECK (personal_growth >= 0 AND personal_growth <= 5)")
    private Double personal_growth;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employeeId", referencedColumnName = "eUserNo", insertable = false, updatable = false)
    private Employee employee;
    @OneToOne
    @MapsId
    @JoinColumn(name = "companyId", referencedColumnName = "cUserNo", insertable = false, updatable = false)
    private Company company;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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
        return team_work;
    }

    public void setTeamWork(Double teamWork) {
        this.team_work = teamWork;
    }

    public Double getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(Double responsibility) {
        this.responsibility = responsibility;
    }

    public Double getPersonalGrowth() {
        return personal_growth;
    }

    public void setPersonalGrowth(Double personalGrowth) {
        this.personal_growth = personalGrowth;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
