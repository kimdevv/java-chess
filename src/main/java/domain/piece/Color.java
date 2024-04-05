package domain.piece;

public enum Color {

    BLACK,
    WHITE,
    NONE;

    public Color changeTurn() {
        if (this == Color.NONE) {
            throw new IllegalArgumentException("Turn으로 가능한 색이 아닙니다.");
        }
        if (this == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
