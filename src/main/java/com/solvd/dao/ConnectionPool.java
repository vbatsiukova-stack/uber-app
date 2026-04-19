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
                pool.add(createConnection());
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
        if (pool.isEmpty()) {
            return createConnection(); // fallback
        }
        return pool.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
        }
    }
}