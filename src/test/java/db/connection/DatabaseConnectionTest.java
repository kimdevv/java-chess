package db.connection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseConnectionTest {

    @DisplayName("데이터베이스에 연결되는지 테스트")
    @Test
    void isDatabaseConnect() {
        assertDoesNotThrow(DatabaseConnection::getConnection);
    }

    @DisplayName("컨낵션을 비우는 메서드 호출 후 컨넥션이 비워지는지 테스트")
    @Test
    void isDatabaseClose() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        DatabaseConnection.tryCloseConnection();

        Assertions.assertThat(connection.isClosed()).isTrue();
    }
}
