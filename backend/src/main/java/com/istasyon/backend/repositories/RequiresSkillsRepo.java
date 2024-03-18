package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.RequiresSkills;
import com.istasyon.backend.repositories.compositeIds.RequiresSkillsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequiresSkillsRepo extends JpaRepository<RequiresSkills, RequiresSkillsId> {
    List<RequiresSkills> findByCompPostsAds_adId(Long adId);
}
