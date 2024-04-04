package chess.model.piece;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public static Color findColorByPieceName(String pieceName) {
        if (pieceName.equals(".")) {
            return NONE;
        }
        if (Character.isUpperCase(pieceName.charAt(0))) {
            return BLACK;
        }
        return WHITE;
    }

    public static Color convertToColor(String colorName) {
        return Arrays.stream(Color.values())
                .filter(color -> color.name().equals(colorName))
                .findFirst()
                .orElse(Color.NONE);
    }

    public Color changeColor(Piece piece) {
        if (piece.lostGoal()) {
            return NONE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNone() {
        return this == NONE;
    }
}
