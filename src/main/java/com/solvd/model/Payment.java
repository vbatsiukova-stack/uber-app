package com.solvd.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private int id;
    private int tripId;
    private String paymentMethod;
    private BigDecimal amount;
    private String paymentStatus;
    private LocalDateTime paidAt;

    public Payment() {
    }

    public Payment(int id, int tripId, String paymentMethod, BigDecimal amount,
                   String paymentStatus, LocalDateTime paidAt) {
        this.id = id;
        this.tripId = tripId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paidAt = paidAt;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paidAt=" + paidAt +
                '}';
    }
}