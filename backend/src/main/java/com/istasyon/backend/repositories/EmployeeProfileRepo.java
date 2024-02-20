package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile, Long> {
    EmployeeProfile findByid(Long id);
}
