package com.solvd.dao;

import com.solvd.model.TripStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripStatusDAO extends AbstractMySQLDAO implements IBaseDAO<TripStatus> {

    private static final String GET_BY_ID = "SELECT * FROM TripStatuses WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM TripStatuses";
    private static final String INSERT = "INSERT INTO TripStatuses(status_name, description, created_at) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE TripStatuses SET status_name = ?, description = ?, created_at = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM TripStatuses WHERE id = ?";

    @Override
    public TripStatus create(TripStatus tripStatus) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setString(1, tripStatus.getStatusName());
            st.setString(2, tripStatus.getDescription());

            if (tripStatus.getCreatedAt() != null) {
                st.setTimestamp(3, Timestamp.valueOf(tripStatus.getCreatedAt()));
            } else {
                st.setNull(3, Types.TIMESTAMP);
            }

            st.executeUpdate();
            st.close();

            return tripStatus;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<TripStatus> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                TripStatus tripStatus = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(tripStatus);
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
    public List<TripStatus> getAll() {
        List<TripStatus> list = new ArrayList<>();
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
    public TripStatus update(TripStatus tripStatus) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setString(1, tripStatus.getStatusName());
            st.setString(2, tripStatus.getDescription());

            if (tripStatus.getCreatedAt() != null) {
                st.setTimestamp(3, Timestamp.valueOf(tripStatus.getCreatedAt()));
            } else {
                st.setNull(3, Types.TIMESTAMP);
            }

            st.setInt(4, tripStatus.getId());

            st.executeUpdate();
            st.close();

            return tripStatus;

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

    private TripStatus mapRow(ResultSet rs) throws SQLException {
        TripStatus tripStatus = new TripStatus();
        tripStatus.setId(rs.getInt("id"));
        tripStatus.setStatusName(rs.getString("status_name"));
        tripStatus.setDescription(rs.getString("description"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            tripStatus.setCreatedAt(createdAt.toLocalDateTime());
        }

        return tripStatus;
    }
}
