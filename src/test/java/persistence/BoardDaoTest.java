package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import database.JdbcTemplate;
import dto.MovementDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionPool;

class BoardDaoTest {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new TestConnectionPool());
    private final BoardDao boardDao = new BoardDaoImpl(jdbcTemplate);

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("DELETE FROM BOARD");
    }

    @Test
    void 보드를_저장한다() {
        int roomId = 1;
        boardDao.save(new MovementDto("a1", "a2"), roomId);

        assertThat(boardDao.findByRoomId(roomId)).hasSize(1);
    }

    @Test
    void 보드를_삭제한다() {
        int roomId = 1;
        boardDao.save(new MovementDto("a1", "a2"), roomId);
        boardDao.save(new MovementDto("a2", "a3"), roomId);
        boardDao.save(new MovementDto("a3", "a4"), roomId);

        boardDao.deleteByRoomId(roomId);

        assertThat(boardDao.findByRoomId(roomId)).isEmpty();
    }
}
