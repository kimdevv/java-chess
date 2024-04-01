package chess.view.dto;

import chess.domain.chessboard.GameResult;

public enum GameResultDto {
    BLACK_WIN("흑이 우승했습니다."),
    WHITE_WIN("백이 우승했습니다."),
    DRAW("무승부 입니다.");

    private final String output;

    GameResultDto(String output) {
        this.output = output;
    }

    public static GameResultDto of(GameResult gameResult) {
        if (gameResult == GameResult.BLACK_WIN) {
            return BLACK_WIN;
        }
        if (gameResult == GameResult.WHITE_WIN) {
            return BLACK_WIN;
        }
        return DRAW;
    }

    public String output() {
        return output;
    }
}
