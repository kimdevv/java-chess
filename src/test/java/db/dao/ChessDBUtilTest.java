package db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessDBUtilTest {

    @DisplayName("DB 연결을 확인한다.")
    @Test
    void getConnection() {
        ChessDBUtil chessDBUtil = ChessDBUtil.getInstance();
        assertThat(chessDBUtil.getConnection()).isNotNull();
    }
}