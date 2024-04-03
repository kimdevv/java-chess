package database;

import database.exception.ConnectionFailedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DefaultConnectionPool implements ConnectionPool {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "CHESS";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int MAX_CONNECTIONS_SIZE = 5;
    private static final boolean THREAD_FAIR_POLICY = true;

    private final BlockingQueue<Connection> connections;

    public DefaultConnectionPool() {
        this.connections = new ArrayBlockingQueue<>(MAX_CONNECTIONS_SIZE, THREAD_FAIR_POLICY);
        for (int i = 0; i < MAX_CONNECTIONS_SIZE; i++) {
            Connection connection = createConnection();
            connections.add(connection);
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionFailedException(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException((e.getMessage()));
        }
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
