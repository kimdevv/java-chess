package chess.domain.piece;

import chess.domain.pieceinfo.Team;

public enum PieceType {
    ROOK("r", "ROOK"),
    KNIGHT("n", "KNIGHT"),
    BISHOP("b", "BISHOP"),
    QUEEN("q", "QUEEN"),
    KING("k", "KING"),
    BLACK_PAWN_FIRST_MOVE("p", "BLACK_PAWN_FIRST_MOVE"),
    BLACK_PAWN_NOT_FIRST_MOVE("p", "BLACK_PAWN_NOT_FIRST_MOVE"),
    WHITE_PAWN_FIRST_MOVE("p", "WHITE_PAWN_FIRST_MOVE"),
    WHITE_PAWN_NOT_FIRST_MOVE("p", "WHITE_PAWN_NOT_FIRST_MOVE"),
    EMPTY(".", "EMPTY");

    private final String pieceLetter;
    private final String pieceType;

    PieceType(String visualizedPiece, String pieceType) {
        this.pieceLetter = visualizedPiece;
        this.pieceType = pieceType;
    }

    public String getPieceLetter(Team team) {
        if (team == Team.BLACK) {
            return pieceLetter.toUpperCase();
        }
        return pieceLetter;
    }

    public String getRawPieceType() {
        return pieceType;
    }

    public static boolean isPawn(PieceType pieceType) {
        return (pieceType == BLACK_PAWN_FIRST_MOVE) ||
                (pieceType == BLACK_PAWN_NOT_FIRST_MOVE) ||
                (pieceType == WHITE_PAWN_FIRST_MOVE) ||
                (pieceType == WHITE_PAWN_NOT_FIRST_MOVE);
    }
}
