package chess.view;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public enum PieceTextMaker {
    KING("♚", "♔"),
    QUEEN("♛", "♕"),
    BISHOP("♝", "♗"),
    KNIGHT("♞", "♘"),
    ROOK("♜", "♖"),
    PAWN("♟", "♙");

    private final String blackText;
    private final String whiteText;

    PieceTextMaker(final String blackText, final String whiteText) {
        this.blackText = blackText;
        this.whiteText = whiteText;
    }

    public static String generateBlackTextOf(final Piece piece) {
        if (piece instanceof King) {
            return KING.blackText;
        }
        if (piece instanceof Queen) {
            return QUEEN.blackText;
        }
        if (piece instanceof Bishop) {
            return BISHOP.blackText;
        }
        if (piece instanceof Knight) {
            return KNIGHT.blackText;
        }
        if (piece instanceof Rook) {
            return ROOK.blackText;
        }
        return PAWN.blackText;
    }

    public static String generateWhiteTextOf(final Piece piece) {
        if (piece instanceof King) {
            return KING.whiteText;
        }
        if (piece instanceof Queen) {
            return QUEEN.whiteText;
        }
        if (piece instanceof Bishop) {
            return BISHOP.whiteText;
        }
        if (piece instanceof Knight) {
            return KNIGHT.whiteText;
        }
        if (piece instanceof Rook) {
            return ROOK.whiteText;
        }
        return PAWN.whiteText;
    }
}
