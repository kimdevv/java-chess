package domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NEUTRALITY;

    public boolean isNotNeutrality() {
        return this != NEUTRALITY;
    }

    public boolean isNotOppositeColor(Color other) {
        if (this == WHITE) {
            return other != BLACK;
        }
        if (this == BLACK) {
            return other != WHITE;
        }
        return false;
    }

    public Color opposite() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NEUTRALITY;
    }

    protected boolean isSameColor(Color other) {
        return this == other;
    }
}
