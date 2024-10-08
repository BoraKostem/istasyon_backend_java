package com.istasyon.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.istasyon.backend.entities.enumeration.JobType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "EmployeePrevJobs")
public class EmployeePrevJobs {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Column
    private Long employeeId;

    @JsonIgnore
    @Column
    private Long companyId;
    @Column
    private String companyName;
    @Column(name = "role")
    private String role;
    @Column(name = "job_description")
    private String jobDescription;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
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
    @ManyToOne
    @JsonIgnore
    @MapKey(name = "employeeId")
    @JoinColumn(name = "employeeId", referencedColumnName = "eUserNo", insertable = false, updatable = false)
    private Employee employee;

    @JsonIgnore
    @ManyToOne
    @MapKey(name = "companyId")
    @JoinColumn(name = "companyId", referencedColumnName = "cUserNo", insertable = false, updatable = false)
    private Company company;

    //TO STRING
    @JsonProperty("Company")
    public String getJobCompany() {
        if(company.getcUserNo() != -1){
            return company.toString();
        }
        return companyName;
    }

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getTeam_work() {
        return team_work;
    }

    public void setTeam_work(Double team_work) {
        this.team_work = team_work;
    }

    public Double getPersonal_growth() {
        return personal_growth;
    }

    public void setPersonal_growth(Double personal_growth) {
        this.personal_growth = personal_growth;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
