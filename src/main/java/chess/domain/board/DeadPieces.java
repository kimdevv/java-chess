package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.ArrayList;
import java.util.List;

public class DeadPieces {
    private final List<Piece> deadPieces;

    public DeadPieces() {
        this.deadPieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        deadPieces.add(piece);
    }

    public boolean isContainKing() {
        return deadPieces.stream()
                .anyMatch(piece -> piece.getType() == PieceType.KING);
    }
}
