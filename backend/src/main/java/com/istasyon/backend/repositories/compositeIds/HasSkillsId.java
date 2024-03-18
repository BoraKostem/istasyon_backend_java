package com.istasyon.backend.repositories.compositeIds;

import java.io.Serializable;
import java.util.Objects;

public class HasSkillsId implements Serializable {
    private Integer skill;
    private Long employee;

    public HasSkillsId() {
    }

    public HasSkillsId(Integer skillId, Long eUserNo) {
        this.skill = skillId;
        this.employee = eUserNo;
    }

    public Integer getSkillId() {
        return skill;
    }

    public void setSkillId(Integer skillId) {
        this.skill = skillId;
    }

    public Long getEUserNo() {
        return employee;
    }

    public void setEUserNo(Long eUserNo) {
        this.employee = eUserNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasSkillsId that = (HasSkillsId) o;
        return Objects.equals(skill, that.skill) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, employee);
    }
}
