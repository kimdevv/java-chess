package chess.dao;

import chess.domain.GameStatus;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessGameDao {
    private static final String GAME_STATUS = "game_status";

    private final Connection connection;

    public ChessGameDao(Connection connection) {
        this.connection = connection;
    }

    public void updateGameStatus(GameStatus gameStatus) {
        final var query = "UPDATE game SET game_status = ? LIMIT 1";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameStatus.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public GameStatus findGameStatus() {
        final var query = String.format("SELECT %s FROM game LIMIT 1", GAME_STATUS);
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return GameStatus.findGameStatus(resultSet.getString(GAME_STATUS));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long findId() {
        final var query = "SELECT id FROM game LIMIT 1";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong("id");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
