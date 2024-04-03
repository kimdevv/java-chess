package chess.database;

import static chess.database.ConnectionConst.DATABASE;
import static chess.database.ConnectionConst.OPTION;
import static chess.database.ConnectionConst.PASSWORD;
import static chess.database.ConnectionConst.SERVER;
import static chess.database.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private DBConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
