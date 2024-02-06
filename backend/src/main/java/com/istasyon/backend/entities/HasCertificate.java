package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Has_Certificate")
public class HasCertificate {

    @Id
    @Column(length = 8)
    private String certificateId;

    @Column(length = 50)
    private String certificateName;

    @Column(length = 50)
    private String companyName;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    // Getters and setters...
}
