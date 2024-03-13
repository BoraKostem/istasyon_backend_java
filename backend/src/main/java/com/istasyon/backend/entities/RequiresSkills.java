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

    public Skills getSkill() {
        return skill;
    }

    public void setSkill(Skills skill) {
        this.skill = skill;
    }

    public CompPostsAds getCompPostsAds() {
        return compPostsAds;
    }

    public void setCompPostsAds(CompPostsAds compPostsAds) {
        this.compPostsAds = compPostsAds;
    }
}
