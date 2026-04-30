package com.solvd.dao;

import com.solvd.model.Promocode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromocodeDAO extends AbstractMySQLDAO implements IBaseDAO<Promocode> {

    private static final String GET_BY_ID = "SELECT * FROM Promocodes WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM Promocodes";
    private static final String INSERT = "INSERT INTO Promocodes(code, discount_type, discount_value, valid_from, valid_to, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Promocodes SET code = ?, discount_type = ?, discount_value = ?, valid_from = ?, valid_to = ?, status = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Promocodes WHERE id = ?";

    @Override
    public Promocode create(Promocode promocode) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(INSERT)) {
                st.setString(1, promocode.getCode());
                st.setString(2, promocode.getDiscountType());

                if (promocode.getDiscountValue() != null) {
                    st.setBigDecimal(3, promocode.getDiscountValue());
                } else {
                    st.setNull(3, Types.DECIMAL);
                }

                if (promocode.getValidFrom() != null) {
                    st.setDate(4, Date.valueOf(promocode.getValidFrom()));
                } else {
                    st.setNull(4, Types.DATE);
                }

                if (promocode.getValidTo() != null) {
                    st.setDate(5, Date.valueOf(promocode.getValidTo()));
                } else {
                    st.setNull(5, Types.DATE);
                }

                st.setString(6, promocode.getStatus());

                st.executeUpdate();
                return promocode;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Optional<Promocode> getById(Long id) {
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
    public List<Promocode> getAll() {
        List<Promocode> list = new ArrayList<>();
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
    public Promocode update(Promocode promocode) {
        Connection connection = null;

        try {
            connection = getConnection();

            try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
                st.setString(1, promocode.getCode());
                st.setString(2, promocode.getDiscountType());

                if (promocode.getDiscountValue() != null) {
                    st.setBigDecimal(3, promocode.getDiscountValue());
                } else {
                    st.setNull(3, Types.DECIMAL);
                }

                if (promocode.getValidFrom() != null) {
                    st.setDate(4, Date.valueOf(promocode.getValidFrom()));
                } else {
                    st.setNull(4, Types.DATE);
                }

                if (promocode.getValidTo() != null) {
                    st.setDate(5, Date.valueOf(promocode.getValidTo()));
                } else {
                    st.setNull(5, Types.DATE);
                }

                st.setString(6, promocode.getStatus());
                st.setInt(7, promocode.getId());

                st.executeUpdate();
                return promocode;
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

    private Promocode mapRow(ResultSet rs) throws SQLException {
        Promocode promocode = new Promocode();
        promocode.setId(rs.getInt("id"));
        promocode.setCode(rs.getString("code"));
        promocode.setDiscountType(rs.getString("discount_type"));
        promocode.setDiscountValue(rs.getBigDecimal("discount_value"));

        Date validFrom = rs.getDate("valid_from");
        if (validFrom != null) {
            promocode.setValidFrom(validFrom.toLocalDate());
        }

        Date validTo = rs.getDate("valid_to");
        if (validTo != null) {
            promocode.setValidTo(validTo.toLocalDate());
        }

        promocode.setStatus(rs.getString("status"));
        return promocode;
    }
}
