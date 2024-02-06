package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Has_Skills")
public class HasSkills {

    @Id
    @ManyToOne
    @JoinColumn(name = "skillId")
    private Skills skill;

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    // Getters and setters...
}
