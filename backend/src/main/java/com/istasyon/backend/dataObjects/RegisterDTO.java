package com.istasyon.backend.dataObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNo;
    private String address = "NULL";
    private Double xCoor = 0.0;
    private Double yCoor = 0.0;
    private String confirmationCode = "-1";
    private LocalDateTime confirmationTime = LocalDateTime.of(2000,1,1,0,0);
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String militaryServiceInfo = "NULL";
    private Boolean driversLicence = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
