package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.HasSkills;
import com.istasyon.backend.repositories.compositeIds.HasSkillsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HasSkillsRepo extends JpaRepository<HasSkills, HasSkillsId> {
    List<HasSkills> findByEmployee_eUserNo(Long eUserNo);
}
