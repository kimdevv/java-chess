package chess.dao;

import chess.db.JdbcTemplate;
import chess.db.RowMapper;
import chess.dto.TurnDto;

import java.util.List;

public class TurnDaoImpl implements TurnDao {
    private static final String TABLE_NAME = "turns";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TurnDto> rowMapper = (resultSet) ->
            new TurnDto(resultSet.getString("turn"));

    public TurnDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TurnDto findOne() {
        final String query = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        final List<TurnDto> turns = jdbcTemplate.executeAndGet(query, rowMapper);
        if (turns.isEmpty()) {
            throw new IllegalArgumentException("데이터가 없습니다.");
        }
        return turns.get(0);
    }

    @Override
    public void update(final TurnDto turnDto) {
        final String deleteQuery = "DELETE FROM " + TABLE_NAME;
        final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?)";
        jdbcTemplate.execute(deleteQuery);
        jdbcTemplate.execute(insertQuery, turnDto.turn());
    }

    @Override
    public void deleteAll() {
        final String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.execute(query);
    }
}
