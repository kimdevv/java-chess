package chess.domain.piece;

public enum PieceType {
    KING,
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    NONE;

    public boolean isPawn() {
        return this == PAWN;
    }
}
