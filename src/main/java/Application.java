import controller.ChessController;
import db.JdbcConnector;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(final String[] args) throws SQLException {
        final Connection connection = JdbcConnector.getMysqlConnection();
        final ChessController chessController = new ChessController(connection);
        chessController.start();
        connection.close();
    }
}
