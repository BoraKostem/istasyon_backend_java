package com.istasyon.backend.entities;

import com.istasyon.backend.entities.enumeration.ApplicationStatus;
import com.istasyon.backend.repositories.compositeIds.ApplicationId;
import jakarta.persistence.*;

@Entity
@IdClass(ApplicationId.class)
@Table(name = "Application")
public class Application {
    @Id
    @ManyToOne
    @JoinColumn(name = "cUserNo")
    private Company company;

    @Id
    @ManyToOne
    @JoinColumn(name = "adId")
    private CompPostsAds compPostsAds;

    @Id
    @ManyToOne
    @JoinColumn(name = "eUserNo")
    private Employee employee;

    private Boolean like_col;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus decision; // PENDING, ACCEPTED, REJECTED

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompPostsAds getCompPostsAds() {
        return compPostsAds;
    }

    public void setCompPostsAds(CompPostsAds compPostsAds) {
        this.compPostsAds = compPostsAds;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getLike_col() {
        return like_col;
    }

    public void setLike_col(Boolean like_col) {
        this.like_col = like_col;
    }

    public ApplicationStatus getDecision() {
        return decision;
    }

    public void setDecision(ApplicationStatus decision) {
        this.decision = decision;
    }

    @Override
    public String toString() {
        return String.format(
                "[compPostsAds=%s, employee=%s, like_col=%s, decision=%s]",
                compPostsAds, employee, like_col, decision);
    }
}

