package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.EmployeePrevJobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeePrevJobsRepo extends JpaRepository<EmployeePrevJobs, Long> {
    EmployeePrevJobs findByid(Long id);
    List<EmployeePrevJobs> findByemployeeId(Long employeeId);
}
