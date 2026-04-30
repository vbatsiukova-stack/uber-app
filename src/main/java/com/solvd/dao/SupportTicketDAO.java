package com.solvd.dao;

import com.solvd.model.SupportTicket;

import java.sql.*;
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

            try (PreparedStatement st = connection.prepareStatement(INSERT)) {
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
                return ticket;
            }

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
    public List<SupportTicket> getAll() {
        List<SupportTicket> list = new ArrayList<>();
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
    public SupportTicket update(SupportTicket ticket) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
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
                return ticket;
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
