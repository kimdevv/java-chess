package dao;

import java.sql.*;

public class ChessGameDaoImpl implements ChessGameDao {

    private final UserDao userDao;

    public ChessGameDaoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveData(String roomName, String boardData, String turnData) {
        createTableIfNotExists();
        final var insertQuery = "INSERT INTO chess(roomName, gameStatus, turn) VALUES(?, ?, ?)";
        try (final var connection = userDao.getConnection();
             final var insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, roomName);
            insertStatement.setString(2, boardData);
            insertStatement.setString(3, turnData);
            insertStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNotExists() {
        final var query = "CREATE TABLE IF NOT EXISTS chess (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "roomName VARCHAR(30) NOT NULL UNIQUE," +
                "gameStatus VARCHAR(500) NOT NULL, " +
                "turn VARCHAR(10) NOT NULL)";
        try (final Connection connection = userDao.getConnection();
             final Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBoard(String roomName) {
        final var query = "DELETE FROM chess WHERE roomName = ?";

        try (Connection connection = userDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, roomName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String dataToBoard(String roomName) {
        String query = "SELECT gameStatus FROM chess WHERE roomName = ?";

        try (Connection connection = userDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, roomName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("gameStatus");
                } else {
                    throw new IllegalArgumentException(roomName + "과 일치하는 방이 없습니다.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String dataToTurn(String roomName) {
        var query = "SELECT turn FROM chess WHERE roomName = ?";

        try (final var connection = userDao.getConnection()) {
            assert connection != null;
            try (final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, roomName);
                final var resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("turn");
                } else {
                    throw new IllegalArgumentException(roomName + "과 일치하는 방이 없어 turn을 찾을 수 없습니다.");
                }
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
