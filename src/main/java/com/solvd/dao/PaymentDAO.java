package com.solvd.dao;

import com.solvd.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAO extends AbstractMySQLDAO implements IBaseDAO<Payment> {

    private static final String GET_BY_ID = "SELECT * FROM Payments WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Payments";
    private static final String INSERT = "INSERT INTO Payments(trip_id, payment_method, amount, payment_status, paid_at) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Payments SET trip_id = ?, payment_method = ?, amount = ?, payment_status = ?, paid_at = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Payments WHERE id = ?";

    @Override
    public Payment create(Payment payment) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setInt(1, payment.getTripId());
            st.setString(2, payment.getPaymentMethod());

            if (payment.getAmount() != null) {
                st.setBigDecimal(3, payment.getAmount());
            } else {
                st.setNull(3, Types.DECIMAL);
            }

            st.setString(4, payment.getPaymentStatus());

            if (payment.getPaidAt() != null) {
                st.setTimestamp(5, Timestamp.valueOf(payment.getPaidAt()));
            } else {
                st.setNull(5, Types.TIMESTAMP);
            }

            st.executeUpdate();
            st.close();

            return payment;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Payment> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Payment payment = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(payment);
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
    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();
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
    public Payment update(Payment payment) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setInt(1, payment.getTripId());
            st.setString(2, payment.getPaymentMethod());

            if (payment.getAmount() != null) {
                st.setBigDecimal(3, payment.getAmount());
            } else {
                st.setNull(3, Types.DECIMAL);
            }

            st.setString(4, payment.getPaymentStatus());

            if (payment.getPaidAt() != null) {
                st.setTimestamp(5, Timestamp.valueOf(payment.getPaidAt()));
            } else {
                st.setNull(5, Types.TIMESTAMP);
            }

            st.setInt(6, payment.getId());

            st.executeUpdate();
            st.close();

            return payment;

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

    private Payment mapRow(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setTripId(rs.getInt("trip_id"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setPaymentStatus(rs.getString("payment_status"));

        Timestamp paidAt = rs.getTimestamp("paid_at");
        if (paidAt != null) {
            payment.setPaidAt(paidAt.toLocalDateTime());
        }

        return payment;
    }
}
