package com.solvd.model;

import java.time.LocalDateTime;

public class Review {
    private int id;
    private int tripId;
    private int passengerId;
    private int driverId;
    private int rating;
    private String commentText;
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(int id, int tripId, int passengerId, int driverId,
                  int rating, String commentText, LocalDateTime createdAt) {
        this.id = id;
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.rating = rating;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", passengerId=" + passengerId +
                ", driverId=" + driverId +
                ", rating=" + rating +
                ", commentText='" + commentText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}