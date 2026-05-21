package com.solvd.model;

import com.solvd.dao.UserDAO;
import com.solvd.json.JacksonParser;
import com.solvd.model.UberData;
import com.solvd.model.User;
import com.solvd.model.Users;
import com.solvd.service.UserService;
import com.solvd.xml.UserJaxbParser;
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

        LOGGER.info("===== JAXB PARSING =====");

        UserJaxbParser jaxbParser = new UserJaxbParser();
        Users users = jaxbParser.readUsers();

        users.getUsers().forEach(u -> LOGGER.info("JAXB User: {}", u));

        LOGGER.info("===== JACKSON PARSING =====");

        JacksonParser jacksonParser = new JacksonParser();
        UberData data = jacksonParser.read();

        if (data.getUsers() != null) {
            data.getUsers().forEach(u -> LOGGER.info("JSON User: {}", u));
        }
    }
}