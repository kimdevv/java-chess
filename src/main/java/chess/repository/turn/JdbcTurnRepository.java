package chess.repository.turn;

import static chess.repository.DatabaseConfig.getConnection;

import chess.domain.piece.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcTurnRepository implements TurnRepository {

    @Override
    public void save(final String color) {
        final var query = "INSERT INTO turn (turn) VALUES(?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] save [TABLE] turn" + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Color> findAny() {
        final var query = "SELECT * FROM turn";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            boolean existTurn = resultSet.next();
            return getTurnDto(resultSet, existTurn);
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] findAll [TABLE] piece" + e.getMessage(), e);
        }
    }

    private Optional<Color> getTurnDto(final ResultSet resultSet, final boolean existTurn)
            throws SQLException {
        if (existTurn) {
            var turnColor = resultSet.getString("turn");
            return Optional.of(Color.valueOf(turnColor));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
        final var query = "DELETE FROM turn";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[METHOD] deleteAll [TABLE] turn" + e.getMessage(), e);
        }
    }
}
