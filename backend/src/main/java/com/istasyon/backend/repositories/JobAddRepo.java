package com.istasyon.backend.repositories;

import com.istasyon.backend.entities.CompPostsAds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAddRepo extends JpaRepository<CompPostsAds, Long> {
    CompPostsAds findByadId(Long adId);
    CompPostsAds findByadIdAndCompany_cUserNo(Long adId, Long cUserNo);
    List<CompPostsAds> findByCompany_cUserNo(Long cUserNo);
    List<CompPostsAds> findByadIdIn(List<Long> adIds);
}
