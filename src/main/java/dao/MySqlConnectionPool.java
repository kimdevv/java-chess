package dao;

import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class MySqlConnectionPool implements ConnectionPool {
    public static final int MAX_CONNECTION_COUNT = 10;

    private final Queue<Connection> connections;

    public MySqlConnectionPool() {
        this.connections = createConnections();
    }

    private static Queue<Connection> createConnections() {
        final Queue<Connection> connections = new LinkedList<>();
        try {
            final DatabaseConfig databaseConfig = new DatabaseConfig();
            for (int i = 0; i < MAX_CONNECTION_COUNT; i++) {
                final Connection connection = createConnection(databaseConfig);
                connections.add(connection);
            }
            return connections;
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return connections;
        }
    }

    private static Connection createConnection(final DatabaseConfig databaseConfig) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + databaseConfig.getServer() + "/" + databaseConfig.getDatabase() + databaseConfig.getOption(),
                databaseConfig.getUsername(),
                databaseConfig.getPassword());
    }

    @Override
    public Connection getConnection() {
        if (connections.isEmpty()) {
            throw new IllegalStateException("커넥션 풀이 비어있습니다.");
        }
        return connections.poll();
    }

    @Override
    public void releaseConnection(final Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connections.add(connection);
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
