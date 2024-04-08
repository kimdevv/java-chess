package chess.view;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum ColorDisplay {
    BLACK(Color.BLACK, "블랙"),
    WHITE(Color.WHITE, "화이트"),
    ;

    private final Color color;
    private final String value;

    ColorDisplay(final Color color, final String value) {
        this.color = color;
        this.value = value;
    }

    public static String getValue(final Color color) {
        ColorDisplay colorDisplay = Arrays.stream(values())
                .filter(display -> color.equals(display.color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 팀 색상이 없습니다."));

        return colorDisplay.value;
    }
}
