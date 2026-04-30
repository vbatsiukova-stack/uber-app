package com.solvd.dao;

import com.solvd.model.CarMaintenance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarMaintenanceDAO extends AbstractMySQLDAO implements IBaseDAO<CarMaintenance> {

    private static final String GET_BY_ID = "SELECT * FROM CarMaintenance WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM CarMaintenance";
    private static final String INSERT = "INSERT INTO CarMaintenance(car_id, service_type, service_date, cost, notes) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE CarMaintenance SET car_id = ?, service_type = ?, service_date = ?, cost = ?, notes = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM CarMaintenance WHERE id = ?";

    @Override
    public CarMaintenance create(CarMaintenance maintenance) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(INSERT)) {
                st.setInt(1, maintenance.getCarId());
                st.setString(2, maintenance.getServiceType());

                if (maintenance.getServiceDate() != null) {
                    st.setDate(3, Date.valueOf(maintenance.getServiceDate()));
                } else {
                    st.setNull(3, Types.DATE);
                }

                if (maintenance.getCost() != null) {
                    st.setBigDecimal(4, maintenance.getCost());
                } else {
                    st.setNull(4, Types.DECIMAL);
                }

                st.setString(5, maintenance.getNotes());

                st.executeUpdate();
                return maintenance;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<CarMaintenance> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(GET_BY_ID)) {
                st.setLong(1, id);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public List<CarMaintenance> getAll() {
        List<CarMaintenance> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(GET_ALL);
                 ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }

        return list;
    }

    @Override
    public CarMaintenance update(CarMaintenance maintenance) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
                st.setInt(1, maintenance.getCarId());
                st.setString(2, maintenance.getServiceType());

                if (maintenance.getServiceDate() != null) {
                    st.setDate(3, Date.valueOf(maintenance.getServiceDate()));
                } else {
                    st.setNull(3, Types.DATE);
                }

                if (maintenance.getCost() != null) {
                    st.setBigDecimal(4, maintenance.getCost());
                } else {
                    st.setNull(4, Types.DECIMAL);
                }

                st.setString(5, maintenance.getNotes());
                st.setInt(6, maintenance.getId());

                st.executeUpdate();
                return maintenance;
            }

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

            try (PreparedStatement st = connection.prepareStatement(DELETE)) {
                st.setLong(1, id);
                return st.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    private CarMaintenance mapRow(ResultSet rs) throws SQLException {
        CarMaintenance maintenance = new CarMaintenance();
        maintenance.setId(rs.getInt("id"));
        maintenance.setCarId(rs.getInt("car_id"));
        maintenance.setServiceType(rs.getString("service_type"));

        Date serviceDate = rs.getDate("service_date");
        if (serviceDate != null) {
            maintenance.setServiceDate(serviceDate.toLocalDate());
        }

        maintenance.setCost(rs.getBigDecimal("cost"));
        maintenance.setNotes(rs.getString("notes"));

        return maintenance;
    }
}