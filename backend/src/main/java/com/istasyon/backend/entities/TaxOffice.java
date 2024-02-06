package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Tax_Office")
public class TaxOffice {

    @Id
    private Integer taxOfficeId;

    @Column(length = 20)
    private String city;

    @Column(length = 30)
    private String taxOfficeName;

    // Getters and setters...
}