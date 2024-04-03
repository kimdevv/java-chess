package persistence;

import database.JdbcTemplate;
import database.RowMapper;
import dto.MovementDto;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<MovementDto> rowMapper = resultSet -> {
        String source = resultSet.getString("SOURCE");
        String target = resultSet.getString("TARGET");
        return new MovementDto(source, target);
    };

    public BoardDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(MovementDto movementDto, int roomId) {
        String query = "INSERT INTO BOARD (SOURCE, TARGET, ROOM_ID) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, movementDto.source(), movementDto.target(), roomId);
    }

    @Override
    public List<MovementDto> findByRoomId(int roomId) {
        String query = "SELECT * FROM BOARD WHERE ROOM_ID = ?";
        return jdbcTemplate.query(query, rowMapper, roomId);
    }

    @Override
    public void deleteByRoomId(int roomId) {
        String query = "DELETE FROM BOARD WHERE ROOM_ID = ?";
        jdbcTemplate.update(query, roomId);
    }
}
