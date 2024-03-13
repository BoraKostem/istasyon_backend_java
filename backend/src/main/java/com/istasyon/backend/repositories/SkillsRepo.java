package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepo extends JpaRepository<Skills, Integer> {
    Skills findBySkillId(Integer skillId);
}
