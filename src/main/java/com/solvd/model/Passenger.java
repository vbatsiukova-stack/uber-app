package com.solvd.model;

public class Passenger {
    private int id;
    private double rating;
    private int bonusPoints;
    private String defaultPaymentMethod;
    private int userId;

    public Passenger() {
    }

    public Passenger(int id, double rating, int bonusPoints, String defaultPaymentMethod, int userId) {
        this.id = id;
        this.rating = rating;
        this.bonusPoints = bonusPoints;
        this.defaultPaymentMethod = defaultPaymentMethod;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public String getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(String defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", rating=" + rating +
                ", bonusPoints=" + bonusPoints +
                ", defaultPaymentMethod='" + defaultPaymentMethod + '\'' +
                ", userId=" + userId +
                '}';
    }
}