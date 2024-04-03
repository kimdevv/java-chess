package domain.piece;

public enum Type {
    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    EMPTY(0);

    private final double score;

    Type(double score) {
        this.score = score;
    }

    public double score() {
        return score;
    }

    public boolean isSameType(Type other) {
        return this == other;
    }
}
