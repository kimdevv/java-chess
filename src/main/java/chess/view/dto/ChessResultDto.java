package chess.view.dto;

import chess.domain.piece.attribute.Color;
import chess.domain.score.Scoreboard;

public class ChessResultDto {

    private final Scoreboard scoreboard;
    private final Color winner;

    public ChessResultDto(final Scoreboard scoreboard, final Color winner) {
        this.scoreboard = scoreboard;
        this.winner = winner;
    }

    public String scores() {
        return String.format(
                "White: %.1f" + "%nBlack: %.1f",
                scoreboard.getScoreByColor(Color.WHITE),
                scoreboard.getScoreByColor(Color.BLACK)
        );
    }

    public String winner() {
        return winner.name();
    }
}
