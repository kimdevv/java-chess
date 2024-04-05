package domain.piece.maker;

import db.entity.Piece;
import domain.piece.Bishop;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.base.ChessPiece;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import view.translator.PieceTranslator;

public class PieceMaker {

    public static ChessPiece mapPiece(Piece piece) {
        PieceTranslator pieceType = PieceTranslator.from(piece);
        Color color = Color.valueOf(piece.getColorType());

        if (pieceType == PieceTranslator.BISHOP) {
            return new Bishop(color);
        }
        if (pieceType == PieceTranslator.KING) {
            return new King(color);
        }
        if (pieceType == PieceTranslator.BLACK_PAWN) {
            return new BlackPawn();
        }
        if (pieceType == PieceTranslator.WHITE_PAWN) {
            return new WhitePawn();
        }
        if (pieceType == PieceTranslator.KNIGHT) {
            return new Knight(color);
        }
        if (pieceType == PieceTranslator.QUEEN) {
            return new Queen(color);
        }
        if (pieceType == PieceTranslator.ROOK) {
            return new Rook(color);
        }
        return new Blank();
    }
}
