package db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcConnectorTest {

    @Test
    @DisplayName("Jdbc가 연결되었는지 확인한다")
    void getConnection() {
        assertThatCode(() -> JdbcConnector.getMysqlConnection()).doesNotThrowAnyException();
    }
}
