package chess.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionManagerTest {

    @Test
    @DisplayName("DB 연결 테스트")
    void getConnection() {
        final ConnectionManager connectionManager = new ConnectionManager();
        final Connection connection = connectionManager.getConnection();

        assertThat(connection).isNotNull();
    }
}
