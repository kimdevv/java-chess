package chess.repository.piece;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.LinkedHashMap;
import java.util.Map;

public class FakePieceRepository implements PieceRepository {
    private Map<Position, Piece> pieces = new LinkedHashMap<>();

    @Override
    public void save(final Piece piece, final Position position) {
        pieces.put(position, piece);
    }

    @Override
    public Map<Position, Piece> findAllPiece() {
        return pieces;
    }

    @Override
    public void deleteAll() {
        pieces = new LinkedHashMap<>();
    }

    @Override
    public boolean existPieces() {
        return !pieces.isEmpty();
    }
}
