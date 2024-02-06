package com.istasyon.backend.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Has_Tax_Office")
public class HasTaxOffice {

    @Id
    @ManyToOne
    @JoinColumn(name = "cUserNo")
    private Company company;

    @Id
    @ManyToOne
    @JoinColumn(name = "taxOfficeId")
    private TaxOffice taxOffice;

    // Getters and setters...
}
