package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.util.List;

public interface PieceDao {
    void addPieceByChessRoomId(PieceDto pieceDto, Long chess_room_id);

    List<PieceDto> findAllByChessRoomId(Long chess_room_id);

    void deleteAllByChessRoomId(Long chess_room_id);
}
