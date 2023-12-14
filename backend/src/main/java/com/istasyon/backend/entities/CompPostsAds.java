package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Comp_Posts_Ads")
public class CompPostsAds {

    @Id
    @Column(length = 8)
    private String adId;

    @Column(length = 50)
    private String jobName;

    private LocalDate publishDate;
    private Integer estimatedSalary;

    @Column(length = 50)
    private String workDays;

    private Double workHours;

    @Column(length = 50)
    private String transportation;

    private Integer ageMin;
    private Integer ageMax;

    @Column(length = 1)
    private String gender;

    private Boolean situation;

    @ManyToOne
    @JoinColumn(name = "c_user_no")
    private Company company;

    // Getters and setters...
}
