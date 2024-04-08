package chess.repository;

import static chess.repository.DatabaseConfig.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DatabaseConfigTest {

    @Test
    void connection() {
        try (final var connection = getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
