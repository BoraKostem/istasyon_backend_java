package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private Long eUserNo;

    @Column(nullable = false)
    private Integer idNo;

    @Column(length = 10)
    private String phoneNo;

    @Column(length = 200)
    private String address;

    private Double xCoor;
    private Double yCoor;

    @Column(length = 6)
    private String confirmationCode;

    private LocalDateTime confirmationTime;

    @Column(length = 1)
    private String gender;

    private LocalDate birthDate;

    @Column(length = 10)
    private String militaryServiceInfo;

    private Boolean driversLicence;

    @Column(length = 200)
    private String infoText;

    @OneToOne
    @MapsId
    @JoinColumn(name = "eUserNo", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    // Getters and setters...
}
