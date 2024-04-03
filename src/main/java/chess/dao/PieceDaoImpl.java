package chess.dao;

import chess.db.JdbcTemplate;
import chess.db.RowMapper;
import chess.dto.PieceDto;

import java.util.List;

public class PieceDaoImpl implements PieceDao {
    private static final String TABLE_NAME = "board";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PieceDto> rowMapper = (resultSet) -> new PieceDto(
            resultSet.getString("board_file"),
            resultSet.getString("board_rank"),
            resultSet.getString("color"),
            resultSet.getString("type")
    );

    public PieceDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final PieceDto piece) {
        final String query = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?)";
        jdbcTemplate.execute(query, piece.file(), piece.rank(), piece.team(), piece.type());
    }

    @Override
    public PieceDto findOne(final String file, final String rank) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE board_file = ? and board_rank = ? limit 1";
        final List<PieceDto> pieces = jdbcTemplate.executeAndGet(query, rowMapper, file, rank);
        if (pieces.isEmpty()) {
            throw new IllegalArgumentException("데이터가 없습니다.");
        }
        return pieces.get(0);
    }

    @Override
    public List<PieceDto> findAll() {
        final String query = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.executeAndGet(query, rowMapper);
    }

    @Override
    public void deleteAll() {
        final String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.execute(query);
    }

    @Override
    public boolean hasRecords() {
        final String query = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        final List<PieceDto> pieces = jdbcTemplate.executeAndGet(query, rowMapper);
        return !pieces.isEmpty();
    }
}
