package domain.piece;

public enum PieceScore {

    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    KING(0),
    ;

    private final double score;

    PieceScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public double toHalfScore() {
        return score / 2;
    }
}
