package chess.domain.game;

public enum Color {
    WHITE,
    BLACK,
    NONE,
    ;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isSame(Color other) {
        return this == other;
    }

    public Color getOpposite() {
        if (this == WHITE) {
            return BLACK;
        }

        if (this == BLACK) {
            return WHITE;
        }

        return this;
    }
}
