package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    private static final int UPPER_DIRECTION = 1;
    private static final int LOWER_DIRECTION = -1;

    public int forwardDirection() {
        if (isWhite()) {
            return UPPER_DIRECTION;
        }
        return LOWER_DIRECTION;
    }

    public Team opposite() {
        if (isWhite()) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
