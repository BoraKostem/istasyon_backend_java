package com.istasyon.backend.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "City_District")
public class CityDistrict {

    @Id
    private Integer districtId;

    @Column(length = 30)
    private String city;

    @Column(length = 30)
    private String district;

    // Getters and setters...
}
