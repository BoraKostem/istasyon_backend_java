package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Requires_Skills")
public class RequiresSkills {

    @Id
    @ManyToOne
    @JoinColumn(name = "skillId")
    private Skills skill;

    @Id
    @ManyToOne
    @JoinColumn(name = "adId")
    private CompPostsAds compPostsAds;

    // Getters and setters...
}
