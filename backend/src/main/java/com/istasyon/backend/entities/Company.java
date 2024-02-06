package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Company")
public class Company {

    @Id
    @Column(length = 10)
    private Long cUserNo;

    @Column(nullable = false)
    private Boolean hasTaxOffice;

    @Column(length = 10, unique = true, nullable = true)
    private String taxNo;

    @Column(length = 80)
    private String companyName;

    @Column(length = 10)
    private String phoneNo;

    @Column(length = 200)
    private String address;

    private Double xCoor;
    private Double yCoor;

    @Column(length = 6)
    private String confirmationCode;

    private LocalDateTime confirmationTime;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cUserNo", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    // Getters and setters...

    public Long getcUserNo() {
        return cUserNo;
    }

    public void setcUserNo(Long cUserNo) {
        this.cUserNo = cUserNo;
    }

    public Boolean getHasTaxOffice() {
        return hasTaxOffice;
    }

    public void setHasTaxOffice(Boolean hasTaxOffice) {
        this.hasTaxOffice = hasTaxOffice;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getxCoor() {
        return xCoor;
    }

    public void setxCoor(Double xCoor) {
        this.xCoor = xCoor;
    }

    public Double getyCoor() {
        return yCoor;
    }

    public void setyCoor(Double yCoor) {
        this.yCoor = yCoor;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public LocalDateTime getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
