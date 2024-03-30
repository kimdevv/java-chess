package chess.domain;

public enum GameStatus {
    WHITE_TURN,
    BLACK_TURN,
    GAME_OVER;

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
