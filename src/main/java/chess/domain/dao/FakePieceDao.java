package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePieceDao implements PieceDao {
    private final Map<Long, List<PieceDto>> boards = new HashMap<>();

    @Override
    public void addPieceByChessRoomId(PieceDto pieceDto, Long chess_room_id) {
        List<PieceDto> board = boards.get(chess_room_id);

        if (board == null) {
            board = new ArrayList<>();
            board.add(pieceDto);
            boards.put(chess_room_id, board);
            return;
        }

        board.add(pieceDto);
    }

    @Override
    public List<PieceDto> findAllByChessRoomId(Long chess_room_id) {
        return boards.get(chess_room_id);
    }

    @Override
    public void deleteAllByChessRoomId(Long chess_room_id) {
        boards.put(chess_room_id, new ArrayList<>());
    }
}
