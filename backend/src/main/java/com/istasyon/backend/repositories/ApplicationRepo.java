package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.Application;
import com.istasyon.backend.repositories.compositeIds.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepo extends JpaRepository<Application, ApplicationId> {
}
