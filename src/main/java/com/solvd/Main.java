package com.solvd;

import com.solvd.dao.UserDAO;
import com.solvd.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        User user = new User();
        user.setFirstName("Anna");
        user.setLastName("Smith");
        user.setEmail("anna" + System.currentTimeMillis() + "@mail.com");
        user.setPhoneNumber("+123" + System.currentTimeMillis());
        user.setCreatedAt(LocalDateTime.now());

        User createdUser = userDAO.create(user);
        System.out.println("Created: " + createdUser);

        System.out.println("All users:");
        System.out.println(userDAO.getAll());

        Optional<User> foundUser = userDAO.getById((long) createdUser.getId());
        System.out.println("Found by id: " + foundUser);

        createdUser.setFirstName("AnnaUpdated");
        createdUser.setEmail("anna.updated@mail.com");
        User updatedUser = userDAO.update(createdUser);
        System.out.println("Updated: " + updatedUser);

        boolean deleted = userDAO.deleteById((long) updatedUser.getId());
        System.out.println("Deleted: " + deleted);
    }
}