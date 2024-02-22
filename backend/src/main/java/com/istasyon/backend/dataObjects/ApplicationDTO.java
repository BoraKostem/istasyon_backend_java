package com.istasyon.backend.dataObjects;

import com.istasyon.backend.entities.enumeration.ApplicationStatus;

public class ApplicationDTO {
    private Long companyId = (long) -1;
    private Long postId = (long) -1;
    private boolean likeOffer = false;
    private ApplicationStatus decision = ApplicationStatus.PENDING; // PENDING, ACCEPTED, REJECTED

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public boolean getLikeOffer() {
        return likeOffer;
    }


    public ApplicationStatus getDecision() {
        return decision;
    }

}
