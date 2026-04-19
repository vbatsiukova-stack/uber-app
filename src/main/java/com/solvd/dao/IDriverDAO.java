package com.solvd.dao;

import com.solvd.model.Driver;

import java.util.List;
import java.util.Optional;

public interface IDriverDAO extends IBaseDAO<Driver> {

    Optional<Driver> getByUserId(int userId);

    List<Driver> getByRatingGreaterThan(double rating);

    List<Driver> getByExperienceGreaterThan(int years);
}