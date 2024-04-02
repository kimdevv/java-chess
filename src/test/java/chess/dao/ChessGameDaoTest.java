package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.GameStatus;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    private ChessGameDao chessGameDao;
    private static Connection connection;

    @BeforeAll
    static void connection() throws SQLException {
        connection = ConnectionGenerator.getConnection();
        connection.setAutoCommit(false);
    }

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao(connection);
    }

    @AfterAll
    static void exit() throws SQLException {
        connection.setAutoCommit(true);
    }

    @AfterEach
    void rollBack() throws SQLException {
        connection.rollback();
    }

    @Test
    @DisplayName("게임 상태를 가져온다.")
    void ChessGameDao_Get_gameStatus() {
        var result = chessGameDao.findGameStatus();
        assertThat(result).isEqualTo(GameStatus.WHITE_TURN);
    }

    @Test
    @DisplayName("체스 게임을 업데이트한다.")
    void ChessGameDao_Add_chessGame() {
        chessGameDao.updateGameStatus(GameStatus.BLACK_TURN);
        var result = chessGameDao.findGameStatus();
        assertThat(result).isEqualTo(GameStatus.BLACK_TURN);
    }

}
