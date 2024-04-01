package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ExecuteQuery<T> {
    T execute(Connection connection) throws SQLException;
}
