package chess.domain.piece;

public enum Side {

    BLACK,
    WHITE,
    EMPTY,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isSame(Side otherSide) {
        return this == otherSide;
    }

    public boolean isOpponentTo(Side other) {
        return this.isBlack() && other.isWhite() || this.isWhite() && other.isBlack();
    }
}
