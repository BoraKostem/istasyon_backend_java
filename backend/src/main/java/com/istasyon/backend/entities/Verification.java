package com.istasyon.backend.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Verification")
public class Verification {

    @Id
    @Column(length = 8)
    private String expId;

    @ManyToOne
    @JoinColumn(name = "cUserNo")
    private Company company;

    // Getters and setters...
}
