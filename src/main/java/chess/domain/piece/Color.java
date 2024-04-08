package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE,
    ;

    public boolean isSameColor(final Color color) {
        return this == color;
    }
}
