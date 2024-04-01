package chess.view.output;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum ColorSymbol {
    WHITE(Color.WHITE, "흰색"),
    BLACK(Color.BLACK, "검정색"),
    NONE(Color.NONE, "없음"),
    ;

    private final Color color;
    private final String symbol;

    ColorSymbol(final Color color, final String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public static ColorSymbol getColorSymbol(final Color color) {
        return Arrays.stream(ColorSymbol.values())
                .filter(colorSymbol -> colorSymbol.color == color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 색상에 맞는 심볼을 찾을 수 없습니다."));
    }

    public Color getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
}
