package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    @DisplayName("체스판에 존재하는 기물의 점수를 계산한다")
    @Test
    void calculate() {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Map<Position, Piece> board = Map.of(
                Position.from("a2"), Pawn.WHITE_PAWN,
                Position.from("b2"), Pawn.WHITE_PAWN,
                Position.from("h7"), Pawn.BLACK_PAWN,
                Position.from("h6"), Pawn.BLACK_PAWN
        );

        ChessGameResult chessGameResult = scoreCalculator.calculate(board);
        Map<Color, Double> resultByColor = chessGameResult.getResult();

        assertThat(resultByColor.get(Color.WHITE)).isEqualTo(2);
        assertThat(resultByColor.get(Color.BLACK)).isEqualTo(1);
        assertThat(chessGameResult.getWinner()).isEqualTo(Color.WHITE);
    }
}
