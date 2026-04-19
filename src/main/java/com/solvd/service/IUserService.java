package com.solvd.service;

import com.solvd.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User create(User user);

    Optional<User> getById(Long id);

    List<User> getAll();

    User update(User user);

    boolean deleteById(Long id);
}