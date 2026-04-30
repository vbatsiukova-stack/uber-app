package com.solvd.dao;

import com.solvd.model.Trip;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDAO extends AbstractMySQLDAO implements IBaseDAO<Trip> {

    private static final String GET_BY_ID = "SELECT * FROM Trips WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Trips";
    private static final String INSERT = "INSERT INTO Trips(passenger_id, driver_id, car_id, status_id, pickup_address, dropoff_address, requested_at, completed_at, price, fare_type_id, promocode_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Trips SET passenger_id = ?, driver_id = ?, car_id = ?, status_id = ?, pickup_address = ?, dropoff_address = ?, requested_at = ?, completed_at = ?, price = ?, fare_type_id = ?, promocode_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Trips WHERE id = ?";

    @Override
    public Trip create(Trip trip) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(INSERT)) {
                st.setInt(1, trip.getPassengerId());
                st.setInt(2, trip.getDriverId());
                st.setInt(3, trip.getCarId());
                st.setInt(4, trip.getStatusId());
                st.setString(5, trip.getPickupAddress());
                st.setString(6, trip.getDropoffAddress());

                if (trip.getRequestedAt() != null) {
                    st.setTimestamp(7, Timestamp.valueOf(trip.getRequestedAt()));
                } else {
                    st.setNull(7, Types.TIMESTAMP);
                }

                if (trip.getCompletedAt() != null) {
                    st.setTimestamp(8, Timestamp.valueOf(trip.getCompletedAt()));
                } else {
                    st.setNull(8, Types.TIMESTAMP);
                }

                BigDecimal price = trip.getPrice();
                if (price != null) {
                    st.setBigDecimal(9, price);
                } else {
                    st.setNull(9, Types.DECIMAL);
                }

                st.setInt(10, trip.getFareTypeId());

                if (trip.getPromocodeId() != null) {
                    st.setInt(11, trip.getPromocodeId());
                } else {
                    st.setNull(11, Types.INTEGER);
                }

                st.executeUpdate();
                return trip;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Trip> getById(Long id) {
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
    public List<Trip> getAll() {
        List<Trip> list = new ArrayList<>();
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
    public Trip update(Trip trip) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
                st.setInt(1, trip.getPassengerId());
                st.setInt(2, trip.getDriverId());
                st.setInt(3, trip.getCarId());
                st.setInt(4, trip.getStatusId());
                st.setString(5, trip.getPickupAddress());
                st.setString(6, trip.getDropoffAddress());

                if (trip.getRequestedAt() != null) {
                    st.setTimestamp(7, Timestamp.valueOf(trip.getRequestedAt()));
                } else {
                    st.setNull(7, Types.TIMESTAMP);
                }

                if (trip.getCompletedAt() != null) {
                    st.setTimestamp(8, Timestamp.valueOf(trip.getCompletedAt()));
                } else {
                    st.setNull(8, Types.TIMESTAMP);
                }

                BigDecimal price = trip.getPrice();
                if (price != null) {
                    st.setBigDecimal(9, price);
                } else {
                    st.setNull(9, Types.DECIMAL);
                }

                st.setInt(10, trip.getFareTypeId());

                if (trip.getPromocodeId() != null) {
                    st.setInt(11, trip.getPromocodeId());
                } else {
                    st.setNull(11, Types.INTEGER);
                }

                st.setInt(12, trip.getId());

                st.executeUpdate();
                return trip;
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

    private Trip mapRow(ResultSet rs) throws SQLException {
        Trip trip = new Trip();

        trip.setId(rs.getInt("id"));
        trip.setPassengerId(rs.getInt("passenger_id"));
        trip.setDriverId(rs.getInt("driver_id"));
        trip.setCarId(rs.getInt("car_id"));
        trip.setStatusId(rs.getInt("status_id"));
        trip.setPickupAddress(rs.getString("pickup_address"));
        trip.setDropoffAddress(rs.getString("dropoff_address"));

        Timestamp requestedAt = rs.getTimestamp("requested_at");
        if (requestedAt != null) {
            trip.setRequestedAt(requestedAt.toLocalDateTime());
        }

        Timestamp completedAt = rs.getTimestamp("completed_at");
        if (completedAt != null) {
            trip.setCompletedAt(completedAt.toLocalDateTime());
        }

        trip.setPrice(rs.getBigDecimal("price"));
        trip.setFareTypeId(rs.getInt("fare_type_id"));

        int promocodeId = rs.getInt("promocode_id");
        if (!rs.wasNull()) {
            trip.setPromocodeId(promocodeId);
        }

        return trip;
    }
}

