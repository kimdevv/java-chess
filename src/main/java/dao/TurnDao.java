package dao;

import domain.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    private final ConnectionPool connectionPool;

    public TurnDao(final ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Team find() {
        final var query = "SELECT team FROM turn";
        final Connection connection = connectionPool.getConnection();

        try (final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createTeam(resultSet);
            }

            connectionPool.releaseConnection(connection);
            throw new SQLException("현재 턴을 DB에서 가져오는데 실패했습니다.");
        } catch (final SQLException e) {
            connectionPool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    private Team createTeam(final ResultSet resultSet) throws SQLException {
        final String team = resultSet.getString("team");
        return Team.from(team);
    }

    public void update(final Team team) throws SQLException {
        final var query = "update turn set team = ?";
        final Connection connection = connectionPool.getConnection();

        try (final var preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            preparedStatement.setString(1, team.name().toLowerCase());
            preparedStatement.executeUpdate();

            connection.commit();
            connectionPool.releaseConnection(connection);
        } catch (final SQLException e) {
            connection.rollback();
            connectionPool.releaseConnection(connection);

            throw new RuntimeException(e);
        }
    }
}
