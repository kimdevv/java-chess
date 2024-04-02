package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import java.util.HashMap;
import java.util.Map;

public class FakeChessRoomDao implements ChessRoomDao {
    private final Map<Long, String> turns = new HashMap<>();

    @Override
    public void addChessRoom(ChessRoomDto chessRoomDto) {
        turns.put(chessRoomDto.id(), chessRoomDto.turn());
    }

    @Override
    public void updateTurnById(String turn, long id) {
        turns.put(id, turn);
    }

    @Override
    public String findTurnById(long id) {
        return turns.get(id);
    }

    @Override
    public void deleteChessRoomById(long id) {
        turns.remove(id);
    }
}
