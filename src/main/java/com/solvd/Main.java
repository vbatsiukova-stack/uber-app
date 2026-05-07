package com.solvd;

import com.solvd.dao.UserDAO;
import com.solvd.json.JacksonParser;
import com.solvd.model.UberData;
import com.solvd.model.User;
import com.solvd.model.Users;
import com.solvd.service.UserService;
import com.solvd.xml.UserJaxbParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.dao.UserMyBatisDAO;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // ===== ТВОЯ СУЩЕСТВУЮЩАЯ ЛОГИКА (DOM DAO) =====
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

        // ===== JAXB ЧАСТЬ =====
        LOGGER.info("===== JAXB PARSING =====");

        UserJaxbParser jaxbParser = new UserJaxbParser();
        Users users = jaxbParser.readUsers();

        users.getUsers().forEach(u -> LOGGER.info("JAXB User: {}", u));

        // ===== JACKSON ЧАСТЬ =====
        LOGGER.info("===== JACKSON PARSING =====");

        JacksonParser jacksonParser = new JacksonParser();
        UberData data = jacksonParser.read();

        if (data.getUsers() != null) {
            data.getUsers().forEach(u -> LOGGER.info("JSON User: {}", u));
        }
        // ===== MYBATIS ЧАСТЬ =====
        LOGGER.info("===== MYBATIS DAO =====");

        UserService myBatisUserService = new UserService(new UserMyBatisDAO());

        User myBatisUser = new User();
        myBatisUser.setFirstName("MyBatis");
        myBatisUser.setLastName("User");
        myBatisUser.setEmail("mybatis" + System.currentTimeMillis() + "@mail.com");
        myBatisUser.setPhoneNumber("+777" + System.currentTimeMillis());
        myBatisUser.setCreatedAt(LocalDateTime.now());

        User createdMyBatisUser = myBatisUserService.create(myBatisUser);
        LOGGER.info("MyBatis created: {}", createdMyBatisUser);

        LOGGER.info("MyBatis all users:");
        LOGGER.info("{}", myBatisUserService.getAll());

        Optional<User> foundMyBatisUser = myBatisUserService.getByEmail(createdMyBatisUser.getEmail());
        LOGGER.info("MyBatis found by email: {}", foundMyBatisUser);

        createdMyBatisUser.setFirstName("MyBatisUpdated");

        User updatedMyBatisUser = myBatisUserService.update(createdMyBatisUser);
        LOGGER.info("MyBatis updated: {}", updatedMyBatisUser);

        boolean deletedMyBatis = myBatisUserService.deleteById(updatedMyBatisUser.getId());
        LOGGER.info("MyBatis deleted: {}", deletedMyBatis);
    }
}