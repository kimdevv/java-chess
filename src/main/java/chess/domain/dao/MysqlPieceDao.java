package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MysqlPieceDao implements PieceDao {
    private static final String TABLE_NAME = "piece";

    private final Connection connection;

    public MysqlPieceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPieceByChessRoomId(PieceDto pieceDto, Long chess_room_id) {
        final var query =
                "INSERT IGNORE INTO " + TABLE_NAME + " (chess_room_id, position, type, team) VALUES(?, ?, ?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);
            statement.setString(2, pieceDto.position());
            statement.setString(3, pieceDto.type());
            statement.setString(4, pieceDto.team());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PieceDto> findAllByChessRoomId(Long chess_room_id) {
        final var query = "SELECT * FROM " + TABLE_NAME + " where chess_room_id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);

            var resultSet = statement.executeQuery();

            List<PieceDto> pieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String type = resultSet.getString("type");
                String team = resultSet.getString("team");

                pieceDtos.add(new PieceDto(position, type, team));
            }

            if (pieceDtos.isEmpty()) {
                throw new NoSuchElementException("ID에 해당하는 피스가 없습니다: " + chess_room_id);
            }

            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllByChessRoomId(Long chess_room_id) {
        final var query = "DELETE FROM " + TABLE_NAME + " where chess_room_id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
