package com.solvd.model;

import java.time.LocalDateTime;

public class SupportTicket {
    private int id;
    private int userId;
    private int tripId;
    private String subject;
    private String messageText;
    private String ticketStatus;
    private LocalDateTime createdAt;

    public SupportTicket() {
    }

    public SupportTicket(int id, int userId, int tripId, String subject,
                         String messageText, String ticketStatus, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.tripId = tripId;
        this.subject = subject;
        this.messageText = messageText;
        this.ticketStatus = ticketStatus;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SupportTicket{" +
                "id=" + id +
                ", userId=" + userId +
                ", tripId=" + tripId +
                ", subject='" + subject + '\'' +
                ", messageText='" + messageText + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}