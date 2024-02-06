package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    boolean existsBycUserNo(Long cUserNo);
    Company findBycUserNo(Long cUserNo);
}
