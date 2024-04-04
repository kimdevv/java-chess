package domain.chessboard;

import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;

public class MovingResult {

    private final ChessPiece movingPiece;
    private final ChessPiece removedPiece;

    public MovingResult(ChessPiece movingPiece, ChessPiece removedPiece) {
        this.movingPiece = movingPiece;
        this.removedPiece = removedPiece;
    }

    public boolean isKingRemoved() {
        return removedPiece.isSameType(PieceType.KING);
    }

    public ChessPiece getMovingPiece() {
        return movingPiece;
    }
}
