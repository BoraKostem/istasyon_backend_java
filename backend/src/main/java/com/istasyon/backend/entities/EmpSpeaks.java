package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Emp_Speaks")
public class EmpSpeaks {

    @Id
    @ManyToOne
    @JoinColumn(name = "languageId")
    private Language language;

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
