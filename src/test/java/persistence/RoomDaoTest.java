package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import database.JdbcTemplate;
import domain.room.Room;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionPool;

class RoomDaoTest {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(new TestConnectionPool());
    private final RoomDao roomDao = new RoomDaoImpl(jdbcTemplate);

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("DELETE FROM ROOM");
    }

    @Test
    void 룸을_저장한다() {
        Room room = new Room("초고수만");

        roomDao.save(room);

        assertThat(roomDao.findAll()).hasSize(1);
    }

    @Test
    void 룸을_삭제한다() {
        Room room = new Room("룸123");
        roomDao.save(room);
        List<Room> rooms = roomDao.findAll();

        roomDao.deleteById(rooms.get(0).getRoomId());

        assertThat(roomDao.findAll()).isEmpty();
    }

    @Test
    void 존재하지_않은_아이디로_룸을_찾으면_empty를_반환한다() {
        Optional<Room> optionalRoom = roomDao.findById(1);

        assertThat(optionalRoom).isEmpty();
    }
}
