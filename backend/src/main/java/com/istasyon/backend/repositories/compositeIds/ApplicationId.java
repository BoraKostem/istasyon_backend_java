package com.istasyon.backend.repositories.compositeIds;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationId implements Serializable {
    private Long company;
    private Long compPostsAds;
    private Long employee;

    public ApplicationId() {
    }

    public ApplicationId(Long company, Long compPostsAds, Long employee) {
        this.company = company;
        this.compPostsAds = compPostsAds;
        this.employee = employee;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    public Long getCompPostsAds() {
        return compPostsAds;
    }

    public void setCompPostsAds(Long compPostsAds) {
        this.compPostsAds = compPostsAds;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationId that = (ApplicationId) o;
        return Objects.equals(company, that.company) &&
                Objects.equals(compPostsAds, that.compPostsAds) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, compPostsAds, employee);
    }
}
