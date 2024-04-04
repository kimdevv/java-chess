package domain.piece;

public enum Color {

    BLACK,
    WHITE,
    ANY;

    public Color getOpponentColor() {
        if (this == ANY) {
            return this;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
