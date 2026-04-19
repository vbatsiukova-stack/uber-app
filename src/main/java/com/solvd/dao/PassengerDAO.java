package com.solvd.dao;

import com.solvd.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassengerDAO extends AbstractMySQLDAO implements IBaseDAO<Passenger> {

    private static final String GET_BY_ID = "SELECT * FROM Passengers WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Passengers";
    private static final String INSERT = "INSERT INTO Passengers(rating, bonus_points, default_payment_method, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Passengers SET rating = ?, bonus_points = ?, default_payment_method = ?, user_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Passengers WHERE id = ?";

    @Override
    public Passenger create(Passenger passenger) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setDouble(1, passenger.getRating());
            st.setInt(2, passenger.getBonusPoints());
            st.setString(3, passenger.getDefaultPaymentMethod());
            st.setInt(4, passenger.getUserId());

            st.executeUpdate();
            st.close();

            return passenger;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Passenger> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Passenger p = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(p);
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
    public List<Passenger> getAll() {
        List<Passenger> list = new ArrayList<>();
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
    public Passenger update(Passenger passenger) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setDouble(1, passenger.getRating());
            st.setInt(2, passenger.getBonusPoints());
            st.setString(3, passenger.getDefaultPaymentMethod());
            st.setInt(4, passenger.getUserId());
            st.setInt(5, passenger.getId());

            st.executeUpdate();
            st.close();

            return passenger;

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

    private Passenger mapRow(ResultSet rs) throws SQLException {
        Passenger p = new Passenger();
        p.setId(rs.getInt("id"));
        p.setRating(rs.getDouble("rating"));
        p.setBonusPoints(rs.getInt("bonus_points"));
        p.setDefaultPaymentMethod(rs.getString("default_payment_method"));
        p.setUserId(rs.getInt("user_id"));
        return p;
    }
}
