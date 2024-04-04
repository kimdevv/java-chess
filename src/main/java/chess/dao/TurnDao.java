package chess.dao;

import chess.domain.chesspiece.Camp;
import java.sql.SQLException;

public class TurnDao {

    private final DBConnection dbConnection = new DBConnection();

    public void initialTurn() {
        final var query = "INSERT INTO turn VALUES(?)";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, Camp.NONE.toString());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTurn(Camp camp) {
        final var query = "UPDATE turn SET camp =? WHERE true";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, camp.toString());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Camp findTurn() {
        final var query = "SELECT * FROM turn";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Camp.findCamp(resultSet.getString("camp"));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExistenceTurn() {
        final var query = "SELECT * FROM turn";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTurn() {
        final var query = "DELETE FROM turn WHERE true";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
