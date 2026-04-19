package com.solvd.dao;

import com.solvd.model.SupportTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupportTicketDAO extends AbstractMySQLDAO implements IBaseDAO<SupportTicket> {

    private static final String GET_BY_ID = "SELECT * FROM SupportTickets WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM SupportTickets";
    private static final String INSERT = "INSERT INTO SupportTickets(user_id, trip_id, subject, message_text, ticket_status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE SupportTickets SET user_id = ?, trip_id = ?, subject = ?, message_text = ?, ticket_status = ?, created_at = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM SupportTickets WHERE id = ?";

    @Override
    public SupportTicket create(SupportTicket ticket) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setInt(1, ticket.getUserId());
            st.setInt(2, ticket.getTripId());
            st.setString(3, ticket.getSubject());
            st.setString(4, ticket.getMessageText());
            st.setString(5, ticket.getTicketStatus());

            if (ticket.getCreatedAt() != null) {
                st.setTimestamp(6, Timestamp.valueOf(ticket.getCreatedAt()));
            } else {
                st.setNull(6, Types.TIMESTAMP);
            }

            st.executeUpdate();
            st.close();

            return ticket;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<SupportTicket> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                SupportTicket ticket = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(ticket);
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
    public List<SupportTicket> getAll() {
        List<SupportTicket> list = new ArrayList<>();
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
    public SupportTicket update(SupportTicket ticket) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setInt(1, ticket.getUserId());
            st.setInt(2, ticket.getTripId());
            st.setString(3, ticket.getSubject());
            st.setString(4, ticket.getMessageText());
            st.setString(5, ticket.getTicketStatus());

            if (ticket.getCreatedAt() != null) {
                st.setTimestamp(6, Timestamp.valueOf(ticket.getCreatedAt()));
            } else {
                st.setNull(6, Types.TIMESTAMP);
            }

            st.setInt(7, ticket.getId());

            st.executeUpdate();
            st.close();

            return ticket;

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

    private SupportTicket mapRow(ResultSet rs) throws SQLException {
        SupportTicket ticket = new SupportTicket();
        ticket.setId(rs.getInt("id"));
        ticket.setUserId(rs.getInt("user_id"));
        ticket.setTripId(rs.getInt("trip_id"));
        ticket.setSubject(rs.getString("subject"));
        ticket.setMessageText(rs.getString("message_text"));
        ticket.setTicketStatus(rs.getString("ticket_status"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            ticket.setCreatedAt(createdAt.toLocalDateTime());
        }

        return ticket;
    }
}
