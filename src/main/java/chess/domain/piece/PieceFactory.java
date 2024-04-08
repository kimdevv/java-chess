package chess.domain.piece;

public class PieceFactory {
    public static Piece create(final PieceType pieceType, final Color color) {
        if (pieceType == PieceType.KING) {
            return new King(color);
        }
        if (pieceType == PieceType.QUEEN) {
            return new Queen(color);
        }
        if (pieceType == PieceType.KNIGHT) {
            return new Knight(color);
        }
        if (pieceType == PieceType.ROOK) {
            return new Rook(color);
        }
        if (pieceType == PieceType.PAWN) {
            return new Pawn(color);
        }
        if (pieceType == PieceType.BISHOP) {
            return new Bishop(color);
        }
        return Empty.getInstance();
    }
}
