package chess;

import java.sql.Connection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {

    @DisplayName("DB 연결이 제대로 되는지 확인한다.")
    @Test
    void databaseConnectionTest() {
        Connection connection = DatabaseConnection.getConnection("chess");
        Assertions.assertThat(connection).isNotNull();
    }
}
