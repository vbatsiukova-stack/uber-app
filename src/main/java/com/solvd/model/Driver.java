package com.solvd.model;

import java.time.LocalDate;

public class Driver {
    private int id;
    private String licenseNumber;
    private String driverStatus;
    private double rating;
    private LocalDate hireDate;
    private int yearsOfExperience;
    private int userId;

    public Driver() {
    }

    public Driver(int id, String licenseNumber, String driverStatus, double rating,
                  LocalDate hireDate, int yearsOfExperience, int userId) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.driverStatus = driverStatus;
        this.rating = rating;
        this.hireDate = hireDate;
        this.yearsOfExperience = yearsOfExperience;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", driverStatus='" + driverStatus + '\'' +
                ", rating=" + rating +
                ", hireDate=" + hireDate +
                ", yearsOfExperience=" + yearsOfExperience +
                ", userId=" + userId +
                '}';
    }
}