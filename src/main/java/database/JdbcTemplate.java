package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate {
    private static final int PK_GENERATION_STRATEGY = Statement.RETURN_GENERATED_KEYS;

    private final ConnectionPool connectionPool;

    public JdbcTemplate(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void update(String query, Object... args) {
        var connection = connectionPool.getConnection();
        try (var preparedStatement = connection.prepareStatement(query, PK_GENERATION_STRATEGY)) {
            setParameters(preparedStatement, args);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setParameters(PreparedStatement preparedStatement, Object[] args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
    }

    public <T> Optional<T> queryForObject(String query, RowMapper<T> rowMapper, Object... args) {
        var connection = connectionPool.getConnection();
        try (var preparedStatement = connection.prepareStatement(query);
             var resultSet = executeQuery(preparedStatement, args)) {
            return mapRow(rowMapper, resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private <T> Optional<T> mapRow(RowMapper<T> rowMapper, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(rowMapper.mapRow(resultSet));
        }
        return Optional.empty();
    }

    public <T> List<T> query(String query, RowMapper<T> rowMapper, Object... args) {
        var connection = connectionPool.getConnection();
        try (var preparedStatement = connection.prepareStatement(query);
             var resultSet = executeQuery(preparedStatement, args)) {
            return mapRows(rowMapper, resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private <T> List<T> mapRows(RowMapper<T> rowMapper, ResultSet resultSet) throws SQLException {
        List<T> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(rowMapper.mapRow(resultSet));
        }
        return results;
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement, Object... args) throws SQLException {
        setParameters(preparedStatement, args);
        return preparedStatement.executeQuery();
    }
}
