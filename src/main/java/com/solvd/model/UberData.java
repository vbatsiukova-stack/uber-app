package com.solvd.model;

import java.util.List;

public class UberData {

    private List<User> users;
    private List<Car> cars;
    private List<Driver> drivers;
    private List<Passenger> passengers;
    private List<Trip> trips;

    public UberData() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public String toString() {
        return "UberData{" +
                "users=" + users +
                ", cars=" + cars +
                ", drivers=" + drivers +
                ", passengers=" + passengers +
                ", trips=" + trips +
                '}';
    }
}
