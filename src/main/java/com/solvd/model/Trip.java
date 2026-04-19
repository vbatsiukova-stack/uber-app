package com.solvd.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trip {
    private int id;
    private int passengerId;
    private int driverId;
    private int carId;
    private int statusId;
    private String pickupAddress;
    private String dropoffAddress;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;
    private BigDecimal price;
    private int fareTypeId;
    private Integer promocodeId;

    public Trip() {
    }

    public Trip(int id, int passengerId, int driverId, int carId, int statusId,
                String pickupAddress, String dropoffAddress, LocalDateTime requestedAt,
                LocalDateTime completedAt, BigDecimal price, int fareTypeId, Integer promocodeId) {
        this.id = id;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.carId = carId;
        this.statusId = statusId;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.requestedAt = requestedAt;
        this.completedAt = completedAt;
        this.price = price;
        this.fareTypeId = fareTypeId;
        this.promocodeId = promocodeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getFareTypeId() {
        return fareTypeId;
    }

    public void setFareTypeId(int fareTypeId) {
        this.fareTypeId = fareTypeId;
    }

    public Integer getPromocodeId() {
        return promocodeId;
    }

    public void setPromocodeId(Integer promocodeId) {
        this.promocodeId = promocodeId;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", driverId=" + driverId +
                ", carId=" + carId +
                ", statusId=" + statusId +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", dropoffAddress='" + dropoffAddress + '\'' +
                ", requestedAt=" + requestedAt +
                ", completedAt=" + completedAt +
                ", price=" + price +
                ", fareTypeId=" + fareTypeId +
                ", promocodeId=" + promocodeId +
                '}';
    }
}