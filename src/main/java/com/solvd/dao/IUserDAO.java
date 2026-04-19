package com.solvd.dao;

import com.solvd.model.User;

import java.util.Optional;

public interface IUserDAO extends IBaseDAO<User> {
    Optional<User> getByEmail(String email);
}