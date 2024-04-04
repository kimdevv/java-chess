package domain;

public enum Side {

    BLACK,
    WHITE,
    NONE,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Side opponent() {
        if (this.isBlack()) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isNotNone() {
        return this != NONE;
    }
}
