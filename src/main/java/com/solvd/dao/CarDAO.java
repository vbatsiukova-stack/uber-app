package com.solvd.dao;

import com.solvd.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAO extends AbstractMySQLDAO implements IBaseDAO<Car> {

    private static final String GET_BY_ID = "SELECT * FROM Cars WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Cars";
    private static final String INSERT = "INSERT INTO Cars(driver_id, brand, model, color, plate_number, year) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Cars SET driver_id = ?, brand = ?, model = ?, color = ?, plate_number = ?, year = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Cars WHERE id = ?";

    @Override
    public Car create(Car car) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setInt(1, car.getDriverId());
            st.setString(2, car.getBrand());
            st.setString(3, car.getModel());
            st.setString(4, car.getColor());
            st.setString(5, car.getPlateNumber());
            st.setInt(6, car.getYear());

            st.executeUpdate();
            st.close();

            return car;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Car> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Car car = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(car);
            }

            rs.close();
            st.close();
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_ALL);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

            rs.close();
            st.close();
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Car update(Car car) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setInt(1, car.getDriverId());
            st.setString(2, car.getBrand());
            st.setString(3, car.getModel());
            st.setString(4, car.getColor());
            st.setString(5, car.getPlateNumber());
            st.setInt(6, car.getYear());
            st.setInt(7, car.getId());

            st.executeUpdate();
            st.close();

            return car;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(DELETE);
            st.setLong(1, id);

            boolean deleted = st.executeUpdate() > 0;
            st.close();

            return deleted;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    private Car mapRow(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setDriverId(rs.getInt("driver_id"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setColor(rs.getString("color"));
        car.setPlateNumber(rs.getString("plate_number"));
        car.setYear(rs.getInt("year"));
        return car;
    }
}