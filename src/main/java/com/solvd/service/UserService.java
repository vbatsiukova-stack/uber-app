package com.solvd.service;

import com.solvd.dao.IUserDAO;
import com.solvd.model.User;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User create(User user) {
        return userDAO.create(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDAO.deleteById(id);
    }
}