package com.istasyon.backend.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Emp_Has_District")
public class EmpHasDistrict {

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "districtId")
    private CityDistrict cityDistrict;

    // Getters and setters...
}
