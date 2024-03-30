package dao;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.TeamColor;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDaoImplTest {
    final GameDao gameDao = new GameDaoImpl();
    Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DBConnector.getInstance().getConnection();
        connection.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    @DisplayName("게임 정보를 추가하면 자동 생성된 게임 ID를 반환한다.")
    void addGameTest() {
        int gameId = gameDao.addGame(connection);
        assertThat(gameId).isPositive();
    }

    @Test
    @DisplayName("게임 ID로 저장된 차례를 조회한다.")
    void findTurnTest() {
        int gameId = gameDao.addGame(connection);
        TeamColor turn = gameDao.findTurn(connection, gameId);
        assertThat(turn).isEqualTo(TeamColor.WHITE);
    }

    @Test
    @DisplayName("저장된 게임에 대한 차례를 변경한다.")
    void updateTurnTest() {
        int gameId = gameDao.addGame(connection);
        gameDao.updateTurn(connection, gameId, TeamColor.BLACK);
        TeamColor turn = gameDao.findTurn(connection, gameId);
        assertThat(turn).isEqualTo(TeamColor.BLACK);
    }
}
