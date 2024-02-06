package com.istasyon.backend.dataObjects;

import java.time.LocalDateTime;

public class CompanyDTO {
    private Boolean hasTaxOffice = false;

    private String taxNo = null;

    private String companyName;
    private String phoneNo;
    private String address = "NULL";
    private Double xCoor = 0.0;
    private Double yCoor = 0.0;
    private String confirmationCode = "-1";
    private LocalDateTime confirmationTime = LocalDateTime.of(2000,1,1,0,0);

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
}
