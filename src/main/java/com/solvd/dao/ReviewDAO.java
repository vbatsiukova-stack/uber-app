package com.solvd.dao;

import com.solvd.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDAO extends AbstractMySQLDAO implements IBaseDAO<Review> {

    private static final String GET_BY_ID = "SELECT * FROM Reviews WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Reviews";
    private static final String INSERT = "INSERT INTO Reviews(trip_id, passenger_id, driver_id, rating, comment_text, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Reviews SET trip_id = ?, passenger_id = ?, driver_id = ?, rating = ?, comment_text = ?, created_at = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Reviews WHERE id = ?";

    @Override
    public Review create(Review review) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setInt(1, review.getTripId());
            st.setInt(2, review.getPassengerId());
            st.setInt(3, review.getDriverId());
            st.setInt(4, review.getRating());
            st.setString(5, review.getCommentText());

            if (review.getCreatedAt() != null) {
                st.setTimestamp(6, Timestamp.valueOf(review.getCreatedAt()));
            } else {
                st.setNull(6, Types.TIMESTAMP);
            }

            st.executeUpdate();
            st.close();

            return review;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Review> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Review review = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(review);
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
    public List<Review> getAll() {
        List<Review> list = new ArrayList<>();
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
    public Review update(Review review) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setInt(1, review.getTripId());
            st.setInt(2, review.getPassengerId());
            st.setInt(3, review.getDriverId());
            st.setInt(4, review.getRating());
            st.setString(5, review.getCommentText());

            if (review.getCreatedAt() != null) {
                st.setTimestamp(6, Timestamp.valueOf(review.getCreatedAt()));
            } else {
                st.setNull(6, Types.TIMESTAMP);
            }

            st.setInt(7, review.getId());

            st.executeUpdate();
            st.close();

            return review;

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

    private Review mapRow(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setId(rs.getInt("id"));
        review.setTripId(rs.getInt("trip_id"));
        review.setPassengerId(rs.getInt("passenger_id"));
        review.setDriverId(rs.getInt("driver_id"));
        review.setRating(rs.getInt("rating"));
        review.setCommentText(rs.getString("comment_text"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            review.setCreatedAt(createdAt.toLocalDateTime());
        }

        return review;
    }
}