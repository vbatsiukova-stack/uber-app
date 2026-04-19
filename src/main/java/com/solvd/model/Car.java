package com.solvd.model;

public class Car {
    private int id;
    private int driverId;
    private String brand;
    private String model;
    private String color;
    private String plateNumber;
    private int year;

    public Car() {
    }

    public Car(int id, int driverId, String brand, String model, String color, String plateNumber, int year) {
        this.id = id;
        this.driverId = driverId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", year=" + year +
                '}';
    }
}
