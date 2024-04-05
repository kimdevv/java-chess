package chess.domain.board;

import chess.domain.piece.Piece;

public enum BoardStatus {

    WHITE_TURN,
    BLACK_TURN,
    KING_DEAD,
    ;

    public static BoardStatus of(Piece sourcePiece, Piece targetPiece) {
        if (targetPiece.isKing()) {
            return KING_DEAD;
        }
        if (sourcePiece.isBlack()) {
            return WHITE_TURN;
        }
        return BLACK_TURN;
    }

    public boolean isWhiteTurn() {
        return this == WHITE_TURN;
    }

    public boolean isBlackTurn() {
        return this == BLACK_TURN;
    }

    public boolean isKingDead() {
        return this == KING_DEAD;
    }
}
