package com.solvd;

import com.solvd.dao.UserDAO;
import com.solvd.model.User;
import com.solvd.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        UserService userService = new UserService(new UserDAO());

        User user = new User();
        user.setFirstName("Anna");
        user.setLastName("Smith");
        user.setEmail("anna" + System.currentTimeMillis() + "@mail.com");
        user.setPhoneNumber("+123" + System.currentTimeMillis());
        user.setCreatedAt(LocalDateTime.now());

        User createdUser = userService.create(user);
        LOGGER.info("Created: {}", createdUser);

        LOGGER.info("All users:");
        LOGGER.info("{}", userService.getAll());

        Optional<User> foundUser = userService.getById(createdUser.getId());
        LOGGER.info("Found by id: {}", foundUser);

        createdUser.setFirstName("AnnaUpdated");
        createdUser.setEmail("anna.updated" + System.currentTimeMillis() + "@mail.com");

        User updatedUser = userService.update(createdUser);
        LOGGER.info("Updated: {}", updatedUser);

        boolean deleted = userService.deleteById(updatedUser.getId());
        LOGGER.info("Deleted: {}", deleted);
    }
}