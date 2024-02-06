package com.istasyon.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private Long eUserNo;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNo;

    @Column(length = 20)
    private String phoneNo;

    @Column(length = 200)
    private String address;

    private Double xCoor;
    private Double yCoor;

    @Column(length = 6)
    private String confirmationCode;

    private LocalDateTime confirmationTime;

    @Column(length = 1)
    private String gender;

    private LocalDate birthDate;

    @Column(length = 10)
    private String militaryServiceInfo;

    private Boolean driversLicence;

    @Column(length = 200)
    private String infoText;

    @OneToOne
    @MapsId
    @JoinColumn(name = "eUserNo", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    // Getters and setters...

    public Long getEUserNo() {
        return eUserNo;
    }

    public void setEUserNo(Long eUserNo) {
        this.eUserNo = eUserNo;
    }

    public Integer getIdNo() {
        return idNo;
    }

    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
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

    public Boolean getDriversLicence() {
        return driversLicence;
    }

    public void setDriversLicence(Boolean driversLicence) {
        this.driversLicence = driversLicence;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
