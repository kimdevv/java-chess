package chess.domain.piece;

import chess.domain.attribute.Score;

public enum PieceType {
    KING(new Score(0)),
    QUEEN(new Score(9)),
    ROOK(new Score(5)),
    BISHOP(new Score(3)),
    KNIGHT(new Score(2.5)),
    PAWN(new Score(1));

    private final Score score;

    PieceType(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
