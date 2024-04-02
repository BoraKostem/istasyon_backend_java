package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "emp_job")
public class EmpJob {
    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;
    @Column(name = "job", length = 500)
    private String job;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
