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

    // Getters and setters...
}
