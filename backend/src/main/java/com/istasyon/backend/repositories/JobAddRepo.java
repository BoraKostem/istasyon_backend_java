package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.CompPostsAds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAddRepo extends JpaRepository<CompPostsAds, Long> {
    CompPostsAds findByadId(Long adId);
}
