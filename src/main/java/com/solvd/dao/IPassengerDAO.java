package com.solvd.dao;

import com.solvd.model.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengerDAO extends IBaseDAO<Passenger> {

    Optional<Passenger> getByUserId(int userId);

    List<Passenger> getByBonusPointsGreaterThan(int bonusPoints);
}