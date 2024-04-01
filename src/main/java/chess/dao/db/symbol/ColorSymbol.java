package chess.dao.db.symbol;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum ColorSymbol {
    WHITE(Color.WHITE, "white"),
    BLACK(Color.BLACK, "black");

    private final Color color;
    private final String symbol;

    ColorSymbol(Color color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public static ColorSymbol getColorSymbolByColor(Color color) {
        return Arrays.stream(ColorSymbol.values()).filter(colorSymbol -> colorSymbol.color == color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("저장할 수 없는 색상입니다."));
    }

    public static ColorSymbol getColorSymbolBySymbol(String symbol) {
        return Arrays.stream(ColorSymbol.values()).filter(colorSymbol -> colorSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불러 올 수 없는 색상입니다."));
    }

    public Color getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }
}
