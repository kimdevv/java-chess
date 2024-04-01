package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class JdbcConnectionTest {

    private final JdbcConnection jdbcConnection = new JdbcConnection();

    @Test
    public void connection() {
        try (final var connection = jdbcConnection.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
