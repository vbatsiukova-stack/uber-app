package com.solvd.dao;

import com.solvd.model.FareType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FareTypeDAO extends AbstractMySQLDAO implements IBaseDAO<FareType> {

    private static final String GET_BY_ID = "SELECT * FROM FareTypes WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM FareTypes";
    private static final String INSERT = "INSERT INTO FareTypes(name, base_price, price_per_km, price_per_minute, description) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE FareTypes SET name = ?, base_price = ?, price_per_km = ?, price_per_minute = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM FareTypes WHERE id = ?";

    @Override
    public FareType create(FareType fareType) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(INSERT);

            st.setString(1, fareType.getName());

            if (fareType.getBasePrice() != null) {
                st.setBigDecimal(2, fareType.getBasePrice());
            } else {
                st.setNull(2, Types.DECIMAL);
            }

            if (fareType.getPricePerKm() != null) {
                st.setBigDecimal(3, fareType.getPricePerKm());
            } else {
                st.setNull(3, Types.DECIMAL);
            }

            if (fareType.getPricePerMinute() != null) {
                st.setBigDecimal(4, fareType.getPricePerMinute());
            } else {
                st.setNull(4, Types.DECIMAL);
            }

            st.setString(5, fareType.getDescription());

            st.executeUpdate();
            st.close();

            return fareType;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<FareType> getById(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(GET_BY_ID);
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                FareType fareType = mapRow(rs);
                rs.close();
                st.close();
                return Optional.of(fareType);
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
    public List<FareType> getAll() {
        List<FareType> list = new ArrayList<>();
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
    public FareType update(FareType fareType) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE);

            st.setString(1, fareType.getName());

            if (fareType.getBasePrice() != null) {
                st.setBigDecimal(2, fareType.getBasePrice());
            } else {
                st.setNull(2, Types.DECIMAL);
            }

            if (fareType.getPricePerKm() != null) {
                st.setBigDecimal(3, fareType.getPricePerKm());
            } else {
                st.setNull(3, Types.DECIMAL);
            }

            if (fareType.getPricePerMinute() != null) {
                st.setBigDecimal(4, fareType.getPricePerMinute());
            } else {
                st.setNull(4, Types.DECIMAL);
            }

            st.setString(5, fareType.getDescription());
            st.setInt(6, fareType.getId());

            st.executeUpdate();
            st.close();

            return fareType;

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

    private FareType mapRow(ResultSet rs) throws SQLException {
        FareType fareType = new FareType();
        fareType.setId(rs.getInt("id"));
        fareType.setName(rs.getString("name"));
        fareType.setBasePrice(rs.getBigDecimal("base_price"));
        fareType.setPricePerKm(rs.getBigDecimal("price_per_km"));
        fareType.setPricePerMinute(rs.getBigDecimal("price_per_minute"));
        fareType.setDescription(rs.getString("description"));
        return fareType;
    }
}
