package com.solvd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "Rom.1289";

    private static final int POOL_SIZE = 5;

    private static ConnectionPool instance;
    private final Queue<Connection> pool = new ArrayDeque<>();

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            for (int i = 0; i < POOL_SIZE; i++) {
                pool.offer(createConnection());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public synchronized Connection getConnection() throws SQLException {
        while (!pool.isEmpty()) {
            Connection connection = pool.poll();

            if (connection != null && !connection.isClosed()) {
                return connection;
            }
        }

        return createConnection();
    }

    public synchronized void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                pool.offer(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error releasing connection", e);
        }
    }
}