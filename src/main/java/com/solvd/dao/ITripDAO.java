package com.solvd.dao;

import com.solvd.model.Trip;

import java.util.List;

public interface ITripDAO extends IBaseDAO<Trip> {

    List<Trip> getByPassengerId(int passengerId);

    List<Trip> getByDriverId(int driverId);

    List<Trip> getByStatusId(int statusId);

    List<Trip> getActiveTrips();
}