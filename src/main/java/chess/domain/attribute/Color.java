package chess.domain.attribute;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK;

    public static Color of(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(colorName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("%s색의 기물이 존재하지 않습니다.".formatted(colorName)));
    }
}
