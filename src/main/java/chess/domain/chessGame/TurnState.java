package chess.domain.chessGame;

import chess.domain.piece.Color;
import java.util.Arrays;

public enum TurnState {
    WAIT(Color.EMPTY, false),
    WHITE(Color.WHITE, true),
    WHITE_INACTIVE(Color.WHITE, false),
    BLACK(Color.BLACK, true),
    BLACK_INACTIVE(Color.BLACK, false),
    END(Color.EMPTY, false);

    private final Color color;
    private final boolean isActive;

    TurnState(Color color, boolean isActive) {
        this.color = color;
        this.isActive = isActive;
    }

    public static TurnState inactivate(TurnState turnState) {
        return Arrays.stream(values())
                .filter(value -> !value.isActive && value.color == turnState.color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("해당 상태를 비활성화 할 수 없습니다: %s", turnState)));
    }

    public static TurnState activate(TurnState turnState) {
        return Arrays.stream(values())
                .filter(value -> value.isActive && value.color == turnState.color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("해당 상태를 활성화 할 수 없습니다: %s", turnState)));
    }

    public static TurnState of(String stateName) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(stateName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("입력된 상태를 찾을 수 없습니다: %s", stateName)));
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Color color() {
        return this.color;
    }
}
