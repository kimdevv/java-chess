package chess.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDaoImpl implements GameDao {

    private final DatabaseConnector dbConnector;

    public GameDaoImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void save(String pieces, String status) {
        createTableIfNotExists();
        String query = "INSERT INTO board(pieces, status) VALUES(?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieces);
            preparedStatement.setString(2, status);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BoardData find() {
        createTableIfNotExists();
        String query = "SELECT pieces, status FROM board ORDER BY id DESC LIMIT 1";
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                String pieces = resultSet.getString("pieces");
                String status = resultSet.getString("status");
                return new BoardData(pieces, status);
            }
            throw new RuntimeException("다시 시작할 체스 게임이 없습니다.");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNotExists() {
        String query = "CREATE TABLE IF NOT EXISTS board (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "pieces VARCHAR(100) NOT NULL, " +
                "status VARCHAR(10) NOT NULL)";
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
