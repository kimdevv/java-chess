package dao;

import domain.game.TeamColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDaoImpl implements GameDao {
    private static final String TABLE_NAME = "game";

    @Override
    public int addGame(Connection connection) {
        final String query = String.format("INSERT INTO %s(turn) VALUE(?);", TABLE_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, TeamColor.WHITE.name());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (final SQLException e) {
            throw new DBException(e);
        }
        throw new DBException("게임 생성 중 오류가 발생했습니다.");
    }

    @Override
    public TeamColor findTurn(Connection connection, int gameId) {
        final String query = String.format("SELECT turn FROM %s WHERE `gameId` = ?", TABLE_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                return TeamColor.valueOf(turn);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        throw new DBException(gameId + " 에 해당하는 차례를 찾을 수 없습니다.");
    }

    @Override
    public void updateTurn(Connection connection, int gameId, TeamColor teamColor) {
        final var query = String.format("UPDATE %s SET turn = ? WHERE gameId = ?", TABLE_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, teamColor.name());
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
