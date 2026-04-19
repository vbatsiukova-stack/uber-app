package com.solvd.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarMaintenance {
    private int id;
    private int carId;
    private String serviceType;
    private LocalDate serviceDate;
    private BigDecimal cost;
    private String notes;

    public CarMaintenance() {
    }

    public CarMaintenance(int id, int carId, String serviceType, LocalDate serviceDate,
                          BigDecimal cost, String notes) {
        this.id = id;
        this.carId = carId;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.cost = cost;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CarMaintenance{" +
                "id=" + id +
                ", carId=" + carId +
                ", serviceType='" + serviceType + '\'' +
                ", serviceDate=" + serviceDate +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }
}
