package db;

import domain.board.position.Position;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MovementDaoImpl implements MovementDao {

    Connection connection;

    public MovementDaoImpl(final Connection connection) {
        this.connection = connection;
    }

    public void createMovement(final Movement movement) {

        final var query = "INSERT INTO movement (source,  target, shape, color) values(?, ?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,
                    movement.source().toFileName().toLowerCase() + (movement.source().toRankIndex() + 1));
            preparedStatement.setString(2,
                    movement.target().toFileName().toLowerCase() + (movement.target().toRankIndex() + 1));
            preparedStatement.setString(3, movement.shape());
            preparedStatement.setString(4, movement.color());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    public List<Movement> findAll() {
        final var query = "SELECT * FROM movement";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            final List<Movement> movements = new ArrayList<>();
            while (resultSet.next()) {
                final String source = resultSet.getString("source");
                final String target = resultSet.getString("target");
                movements.add(new Movement(
                        Position.from(String.valueOf(source.charAt(0)), String.valueOf(source.charAt(1))),
                        Position.from(String.valueOf(target.charAt(0)), String.valueOf(target.charAt(1))),
                        resultSet.getString("shape"),
                        resultSet.getString("color")
                ));
            }
            return movements;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE from movement";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
