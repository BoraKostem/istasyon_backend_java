package com.istasyon.backend.repositories.compositeIds;

import java.io.Serializable;
import java.util.Objects;

public class RequiresSkillsId implements Serializable {
    private Integer skill;
    private Long compPostsAds;

    public RequiresSkillsId() {
    }

    public RequiresSkillsId(Integer skillId, Long compPostsAdsId) {
        this.skill = skillId;
        this.compPostsAds = compPostsAdsId;
    }

    public Integer getSkillId() {
        return skill;
    }

    public void setSkillId(Integer skillId) {
        this.skill = skillId;
    }

    public Long getCompPostsAdsId() {
        return compPostsAds;
    }

    public void setCompPostsAdsId(Long compPostsAdsId) {
        this.compPostsAds = compPostsAdsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequiresSkillsId that = (RequiresSkillsId) o;
        return Objects.equals(skill, that.skill) &&
                Objects.equals(compPostsAds, that.compPostsAds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, compPostsAds);
    }
}