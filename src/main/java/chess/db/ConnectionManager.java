package chess.db;

import chess.dao.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final String database;

    public ConnectionManager(String database) {
        this.database = database;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + database + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new DatabaseException("연결 오류", e);
        }
    }
}
