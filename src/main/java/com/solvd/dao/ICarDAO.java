package com.solvd.dao;

import com.solvd.model.Car;

import java.util.List;

public interface ICarDAO extends IBaseDAO<Car> {

    List<Car> getByDriverId(int driverId);

    Car getByPlateNumber(String plateNumber);
}