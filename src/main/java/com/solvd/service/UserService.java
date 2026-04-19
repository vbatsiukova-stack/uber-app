package com.solvd.service;

import com.solvd.dao.IUserDAO;
import com.solvd.model.User;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        if (userDAO == null) {
            throw new IllegalArgumentException("UserDAO cannot be null");
        }
        this.userDAO = userDAO;
    }

    @Override
    public User create(User user) {
        validateUser(user);
        return userDAO.create(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        validateId(id);
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User update(User user) {
        validateUser(user);
        validateId(user.getId());
        return userDAO.update(user);
    }

    @Override
    public boolean deleteById(Long id) {
        validateId(id);
        return userDAO.deleteById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be positive and not null");
        }
    }
}