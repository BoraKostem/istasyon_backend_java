package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Has_Education")
public class HasEducation {

    @Id
    @Column(length = 8)
    private String educationId;

    @Column(length = 50)
    private String schoolName;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    // Getters and setters...
}
