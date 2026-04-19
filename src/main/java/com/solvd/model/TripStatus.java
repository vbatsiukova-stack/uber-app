package com.solvd.model;

import java.time.LocalDateTime;

public class TripStatus {
    private int id;
    private String statusName;
    private String description;
    private LocalDateTime createdAt;

    public TripStatus() {
    }

    public TripStatus(int id, String statusName, String description, LocalDateTime createdAt) {
        this.id = id;
        this.statusName = statusName;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TripStatus{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}