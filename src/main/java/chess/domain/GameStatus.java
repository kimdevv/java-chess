package chess.domain;

import java.util.Arrays;

public enum GameStatus {
    WHITE_TURN,
    BLACK_TURN,
    GAME_OVER;

    public static GameStatus findGameStatus(String value) {
        return Arrays.stream(values())
                .filter(gameStatus -> gameStatus.name().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게임 상태입니다."));
    }

    public GameStatus changeTurn() {
        if(this == GAME_OVER) {
            throw new IllegalStateException("이미 게임이 종료되었습니다.");
        }
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }
}
