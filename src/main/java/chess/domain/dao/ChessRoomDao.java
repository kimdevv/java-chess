package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;

public interface ChessRoomDao {
    void addChessRoom(ChessRoomDto chessRoomDto);

    void updateTurnById(String turn, long id);

    String findTurnById(long id);

    void deleteChessRoomById(long id);
}
