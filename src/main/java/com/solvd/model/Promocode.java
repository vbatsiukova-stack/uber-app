package com.solvd.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Promocode {
    private int id;
    private String code;
    private String discountType;
    private BigDecimal discountValue;
    private LocalDate validFrom;
    private LocalDate validTo;
    private String status;

    public Promocode() {
    }

    public Promocode(int id, String code, String discountType, BigDecimal discountValue,
                     LocalDate validFrom, LocalDate validTo, String status) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Promocode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", status='" + status + '\'' +
                '}';
    }
}