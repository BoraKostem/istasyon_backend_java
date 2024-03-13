package com.istasyon.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Skills")
public class Skills {

    @Id
    private Integer skillId;

    @Column(length = 30)
    private String skillName;

    // Getters and setters...

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
