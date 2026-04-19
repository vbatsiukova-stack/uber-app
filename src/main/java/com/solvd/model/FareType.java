package com.solvd.model;

import java.math.BigDecimal;

public class FareType {
    private int id;
    private String name;
    private BigDecimal basePrice;
    private BigDecimal pricePerKm;
    private BigDecimal pricePerMinute;
    private String description;

    public FareType() {
    }

    public FareType(int id, String name, BigDecimal basePrice, BigDecimal pricePerKm,
                    BigDecimal pricePerMinute, String description) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.pricePerKm = pricePerKm;
        this.pricePerMinute = pricePerMinute;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(BigDecimal pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FareType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", pricePerKm=" + pricePerKm +
                ", pricePerMinute=" + pricePerMinute +
                ", description='" + description + '\'' +
                '}';
    }
}