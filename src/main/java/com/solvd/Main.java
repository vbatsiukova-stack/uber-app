package com.solvd;

import com.solvd.dao.UserDAO;
import com.solvd.model.User;
import com.solvd.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // создаём DAO и передаём в сервис
        UserService userService = new UserService(new UserDAO());

        // CREATE
        User user = new User();
        user.setFirstName("Anna");
        user.setLastName("Smith");
        user.setEmail("anna" + System.currentTimeMillis() + "@mail.com");
        user.setPhoneNumber("+123" + System.currentTimeMillis());
        user.setCreatedAt(LocalDateTime.now());

        User createdUser = userService.create(user);
        System.out.println("Created: " + createdUser);

        // READ ALL
        System.out.println("All users:");
        System.out.println(userService.getAll());

        // READ BY ID
        Optional<User> foundUser = userService.getById((long) createdUser.getId());
        System.out.println("Found by id: " + foundUser);

        // UPDATE
        createdUser.setFirstName("AnnaUpdated");
        createdUser.setEmail("anna.updated@mail.com");
        User updatedUser = userService.update(createdUser);
        System.out.println("Updated: " + updatedUser);

        // DELETE
        boolean deleted = userService.deleteById((long) updatedUser.getId());
        System.out.println("Deleted: " + deleted);
    }
}