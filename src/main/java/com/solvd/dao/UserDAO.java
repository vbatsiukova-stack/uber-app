package com.solvd.dao;

import com.solvd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Error creating user: no rows inserted");
            }

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating user", e);
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing resources", e);
            }
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by id", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing resources", e);
            }
            releaseConnection(connection);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_ALL);
            rs = statement.executeQuery();

            while (rs.next()) {
                users.add(mapRow(rs));
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing resources", e);
            }
            releaseConnection(connection);
        }
    }

    @Override
    public User update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE);

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

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing statement", e);
            }
            releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing statement", e);
            }
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(GET_BY_EMAIL);
            statement.setString(1, email);

            rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by email", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing resources", e);
            }
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