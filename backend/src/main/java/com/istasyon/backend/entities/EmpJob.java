package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "emp_job")
public class EmpJob {
    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;
    @Column(name = "skills", length = 500)
    private String skills;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
