package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE,
    NONE,
    ;

    public static final Color FIRST_TURN_COLOR = Color.WHITE;

    public Color opposite() {
        if (this == Color.BLACK) {
            return Color.WHITE;
        }
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        throw new IllegalArgumentException("색깔이 존재하지 않아 보색을 반환할 수 없습니다.");
    }
}
