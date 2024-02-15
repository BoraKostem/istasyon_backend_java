package com.istasyon.backend.dataObjects;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class EmployeeDTO {
    private String phoneNo;
    private String address = "NULL";
    private Double xCoor = 0.0;
    private Double yCoor = 0.0;
    private String confirmationCode = "-1";
    private LocalDateTime confirmationTime = LocalDateTime.of(2000,1,1,0,0);
    private String gender;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String militaryServiceInfo = "NULL";
    private Boolean driversLicence = false;
    private String infoText;
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

    public Boolean getDriversLicence() {
        return driversLicence;
    }

    public void setDriversLicence(Boolean driversLicence) {
        this.driversLicence = driversLicence;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMilitaryServiceInfo() {
        return militaryServiceInfo;
    }

    public void setMilitaryServiceInfo(String militaryServiceInfo) {
        this.militaryServiceInfo = militaryServiceInfo;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}