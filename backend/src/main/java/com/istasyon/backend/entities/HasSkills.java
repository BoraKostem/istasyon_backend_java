package com.istasyon.backend.entities;

import com.istasyon.backend.repositories.compositeIds.HasSkillsId;
import jakarta.persistence.*;

@Entity
@IdClass(HasSkillsId.class)
@Table(name = "Has_Skills")
public class HasSkills {

    @Id
    @ManyToOne
    @JoinColumn(name = "skillId")
    private Skills skill;

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    // Getters and setters...

    public Skills getSkill() {
        return skill;
    }

    public void setSkill(Skills skill) {
        this.skill = skill;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
