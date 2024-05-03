package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepo  extends JpaRepository<CompanyProfile, Long> {
    CompanyProfile findByid(Long id);
}
