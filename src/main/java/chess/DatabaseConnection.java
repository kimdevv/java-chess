package chess;

import chess.view.OutputView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {
    }

    public static Connection getConnection(String databaseName) {
        Connection connection = null;
        String url = "jdbc:mysql://" + SERVER + "/" + databaseName + OPTION;

        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
        }

        return connection;
    }
}
