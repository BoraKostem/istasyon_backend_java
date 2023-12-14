package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Skills")
public class Skills {

    @Id
    private Integer skillId;

    @Column(length = 30)
    private String skillName;

    // Getters and setters...
}
