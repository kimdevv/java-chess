package persistence;

import database.JdbcTemplate;
import database.RowMapper;
import domain.room.Room;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Room> rowMapper = resultSet -> {
        int roomId = resultSet.getInt("ROOM_ID");
        String name = resultSet.getString("NAME");
        return new Room(roomId, name);
    };

    public RoomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Room room) {
        String query = "INSERT INTO ROOM (NAME) VALUES (?)";
        jdbcTemplate.update(query, room.getName());
    }

    @Override
    public List<Room> findAll() {
        String query = "SELECT * FROM ROOM";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Room> findById(int id) {
        String query = "SELECT * FROM ROOM WHERE ROOM_ID = ?";
        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }

    @Override
    public void deleteById(int roomId) {
        String query = "DELETE FROM ROOM WHERE ROOM_ID = ?";
        jdbcTemplate.update(query, roomId);
    }
}
