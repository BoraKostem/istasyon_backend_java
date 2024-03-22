package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.Application;
import com.istasyon.backend.entities.CompPostsAds;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.repositories.compositeIds.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, ApplicationId> {
    List<Application> findByCompany(Company company);
    List<Application> findByCompanyAndCompPostsAds(Company company, CompPostsAds compPostsAds);
}
