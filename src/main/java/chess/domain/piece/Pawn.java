package chess.domain.piece;

public class Pawn extends Piece {

    private Pawn(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Piece of(final Color color) {
        if (color == Color.WHITE) {
            return new Pawn(PieceType.WHITE_PAWN, Color.WHITE);
        }
        return new Pawn(PieceType.BLACK_PAWN, Color.BLACK);
    }
}
