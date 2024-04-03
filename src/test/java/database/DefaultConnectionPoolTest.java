package database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DefaultConnectionPoolTest {
    private final ConnectionPool connectionPool = new DefaultConnectionPool();

    @Test
    void 데이터베이스와_커넥션을_맺는다() {
        Connection connection = connectionPool.getConnection();

        assertThat(connection).isNotNull();
    }
}
