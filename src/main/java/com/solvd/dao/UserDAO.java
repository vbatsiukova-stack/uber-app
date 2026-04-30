package com.solvd.dao;

import com.solvd.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO {

    private static final String INSERT =
            "INSERT INTO Users(first_name, last_name, email, phone_number, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_BY_ID =
            "SELECT * FROM Users WHERE id = ?";
    private static final String GET_ALL =
            "SELECT * FROM Users";
    private static final String UPDATE =
            "UPDATE Users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, created_at = ? WHERE id = ?";
    private static final String DELETE_BY_ID =
            "DELETE FROM Users WHERE id = ?";
    private static final String GET_BY_EMAIL =
            "SELECT * FROM Users WHERE email = ?";

    @Override
    public User create(User user) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPhoneNumber());
                statement.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted == 0) {
                    throw new RuntimeException("Error creating user: no rows inserted");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }

                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
                statement.setLong(1, id);

                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by id", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(GET_ALL);
                 ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    users.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users", e);
        } finally {
            releaseConnection(connection);
        }

        return users;
    }

    @Override
    public User update(User user) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPhoneNumber());
                statement.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
                statement.setLong(6, user.getId());

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new RuntimeException("Error updating user: no user found with id " + user.getId());
                }

                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
                statement.setLong(1, id);
                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL)) {
                statement.setString(1, email);

                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by email", e);
        } finally {
            releaseConnection(connection);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            user.setCreatedAt(createdAt.toLocalDateTime());
        }

        return user;
    }
}