package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    boolean existsByeUserNo(Long eUserNo);
    Employee findByeUserNo(Long eUserNo);
}
