package chess.domain.game;

import chess.domain.piece.Side;

public record Score(double blackScore, double whiteScore) {

    public Side winSide() {
        if (blackScore > whiteScore) {
            return Side.BLACK;
        }
        if (blackScore < whiteScore) {
            return Side.WHITE;
        }
        return Side.EMPTY;
    }
}
