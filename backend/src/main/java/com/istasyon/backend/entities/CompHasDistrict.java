package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Comp_Has_District")
public class CompHasDistrict {

    @Id
    @ManyToOne
    @JoinColumn(name = "cUserNo")
    private Company company;

    @Id
    @ManyToOne
    @JoinColumn(name = "districtId")
    private CityDistrict cityDistrict;

    // Getters and setters...
}
