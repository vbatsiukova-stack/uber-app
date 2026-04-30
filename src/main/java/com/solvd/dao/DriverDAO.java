package com.solvd.dao;

import com.solvd.model.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDAO extends AbstractMySQLDAO implements IBaseDAO<Driver> {

    private static final String GET_BY_ID = "SELECT * FROM Drivers WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Drivers";
    private static final String INSERT =
            "INSERT INTO Drivers(license_number, driver_status, rating, hire_date, years_of_experience, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE Drivers SET license_number = ?, driver_status = ?, rating = ?, hire_date = ?, years_of_experience = ?, user_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM Drivers WHERE id = ?";

    @Override
    public Driver create(Driver driver) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(INSERT)) {

                statement.setString(1, driver.getLicenseNumber());
                statement.setString(2, driver.getDriverStatus());
                statement.setDouble(3, driver.getRating());

                if (driver.getHireDate() != null) {
                    statement.setDate(4, Date.valueOf(driver.getHireDate()));
                } else {
                    statement.setNull(4, Types.DATE);
                }

                statement.setInt(5, driver.getYearsOfExperience());
                statement.setInt(6, driver.getUserId());

                statement.executeUpdate();
                return driver;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating driver", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Driver> getById(Long id) {
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
            throw new RuntimeException("Error getting driver by id", e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(GET_ALL);
                 ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    drivers.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting all drivers", e);
        } finally {
            releaseConnection(connection);
        }

        return drivers;
    }

    @Override
    public Driver update(Driver driver) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {

                statement.setString(1, driver.getLicenseNumber());
                statement.setString(2, driver.getDriverStatus());
                statement.setDouble(3, driver.getRating());

                if (driver.getHireDate() != null) {
                    statement.setDate(4, Date.valueOf(driver.getHireDate()));
                } else {
                    statement.setNull(4, Types.DATE);
                }

                statement.setInt(5, driver.getYearsOfExperience());
                statement.setInt(6, driver.getUserId());
                statement.setInt(7, driver.getId());

                statement.executeUpdate();
                return driver;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating driver", e);
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
            throw new RuntimeException("Error deleting driver", e);
        } finally {
            releaseConnection(connection);
        }
    }

    private Driver mapRow(ResultSet rs) throws SQLException {
        Driver driver = new Driver();
        driver.setId(rs.getInt("id"));
        driver.setLicenseNumber(rs.getString("license_number"));
        driver.setDriverStatus(rs.getString("driver_status"));
        driver.setRating(rs.getDouble("rating"));

        Date hireDate = rs.getDate("hire_date");
        if (hireDate != null) {
            driver.setHireDate(hireDate.toLocalDate());
        }

        driver.setYearsOfExperience(rs.getInt("years_of_experience"));
        driver.setUserId(rs.getInt("user_id"));

        return driver;
    }
}