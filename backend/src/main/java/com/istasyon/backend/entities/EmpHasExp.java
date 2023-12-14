package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Emp_Has_Exp")
public class EmpHasExp {

    @Id
    @Column(length = 8)
    private String expId;

    @Column(length = 50)
    private String usedSkills;

    @Column(length = 100)
    private String jobDescription;

    @Column(length = 30)
    private String role;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    // Getters and setters...
}
