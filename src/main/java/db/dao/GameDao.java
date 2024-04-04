package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final GameDao instance = new GameDao();
    private static final ChessDBUtil chessDBUtil = ChessDBUtil.getInstance();

    private GameDao() {
    }

    public static GameDao getInstance() {
        return instance;
    }

    public void createColor() {
        final String query = "INSERT INTO game VALUES(?, ?)";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "WHITE");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateColor(String movingColor) {
        final String query = "UPDATE game SET moving_color = ? WHERE game_id = 1";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movingColor);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadColor() {
        final String query = "SELECT moving_color FROM game WHERE game_id = 1";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getColor(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getColor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("moving_color");
        }
        throw new IllegalStateException("게임이 존재하지 않습니다");
    }

    public void delete() {
        final String query = "DELETE FROM game WHERE game_id = 1";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("게임이 존재하지 않습니다.");
        }
    }
}
