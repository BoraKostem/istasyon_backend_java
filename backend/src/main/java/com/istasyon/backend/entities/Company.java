package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Company")
public class Company {

    @Id
    @Column(length = 10)
    private Long cUserNo;

    @Column(length = 10, unique = true)
    private String taxNo;

    @Column(length = 80)
    private String companyName;

    @Column(length = 10)
    private String phoneNo;

    @Column(length = 200)
    private String address;

    private Double xCoor;
    private Double yCoor;

    @Column(length = 6)
    private String confirmationCode;

    private LocalDateTime confirmationTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cUserNo", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    // Getters and setters...
}
