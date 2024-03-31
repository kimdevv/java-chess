package chess.db;

import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.List;

public class FakePiecesDao implements PiecesDao {
    private final List<PieceDto> pieces = new ArrayList<>();

    @Override
    public void create(PieceDto pieceDto) {
        pieces.add(pieceDto);
    }

    @Override
    public List<PieceDto> findAll() {
        return pieces;
    }

    @Override
    public void deleteAll() {
        pieces.clear();
    }

    @Override
    public int count() {
        return pieces.size();
    }
}
