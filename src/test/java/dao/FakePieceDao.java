package dao;

import dto.PieceDto;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePieceDao implements PieceDao {
    private static final Map<Integer, List<PieceDto>> data = new HashMap<>();

    static {
        data.put(1, List.of(
                new PieceDto(1, 1, "BLACK", "BLACK_PAWN"),
                new PieceDto(5, 3, "WHITE", "WHITE_PAWN")
                ));
        data.put(2, List.of(
                new PieceDto(2, 2, "BLACK", "BLACK_KING"),
                new PieceDto(4, 7, "WHITE", "WHITE_QUEEN")
                ));
    }

    @Override
    public void addAll(Connection notUsed, List<PieceDto> pieceDtos, int gameId) {
        data.put(gameId, new ArrayList<>(pieceDtos));
    }

    @Override
    public List<PieceDto> findAllPieces(Connection notUsed, int gameId) {
        return new ArrayList<>(data.get(gameId));
    }
}
