package com.solvd.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractMySQLDAO {

    protected Connection getConnection() throws SQLException {
        return ConnectionPool.getInstance().getConnection();
    }

    protected void releaseConnection(Connection connection) {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}