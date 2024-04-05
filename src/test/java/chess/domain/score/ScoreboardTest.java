package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.piece.attribute.Color;

class ScoreboardTest {

    static Stream<Arguments> scoreByColor() {
        return Stream.of(
                Arguments.of(ChessboardFactory.create(), WHITE, 38),
                Arguments.of(ChessboardFactory.create(), BLACK, 38)
        );
    }

    @DisplayName("체스 게임의 점수를 계산할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void scoreByColor(Chessboard chessboard, Color color, int expected) {
        Scoreboard scoreboard = new Scoreboard(chessboard);
        double actual = scoreboard.getScoreByColor(color);
        assertThat(actual).isEqualTo(expected);
    }
}
