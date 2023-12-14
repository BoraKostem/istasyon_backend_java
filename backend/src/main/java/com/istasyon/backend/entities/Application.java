package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Application")
public class Application {
    @Id
    @ManyToOne
    @JoinColumn(name = "cUserNo")
    private Company company;

    @Id
    @ManyToOne
    @JoinColumn(name = "addId")
    private CompPostsAds compPostsAds;

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    private Boolean like_col;
    private Boolean decision;

    // Getters and setters...
}

