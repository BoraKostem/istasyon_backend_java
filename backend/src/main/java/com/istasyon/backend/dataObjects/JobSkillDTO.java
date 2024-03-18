package com.istasyon.backend.dataObjects;

import java.util.List;

public class JobSkillDTO {
    private List<Integer> skills;
    private Long adId;

    // Getters and setters...

    public List<Integer> getSkills() {
        return skills;
    }
    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
