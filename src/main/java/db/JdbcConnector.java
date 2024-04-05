package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    public static Connection getMysqlConnection() {
        final String SERVER = "localhost:13306";
        final String DATABASE = "chess";
        final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
