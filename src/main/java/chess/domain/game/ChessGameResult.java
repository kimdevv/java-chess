package chess.domain.game;

import chess.domain.board.Board;

public enum ChessGameResult {
    WHITE_WIN,
    BLACK_WIN,
    NOT_END;

    public static ChessGameResult from(Board board) {
        if (board.isKingDead(Color.WHITE)) {
            return BLACK_WIN;
        }

        if (board.isKingDead(Color.BLACK)) {
            return WHITE_WIN;
        }

        return NOT_END;
    }

    public boolean isKingDead() {
        return this != NOT_END;
    }
}
