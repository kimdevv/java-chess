package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.BlankBoard;
import chess.domain.board.Board;
import chess.domain.color.Color;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreManagerTest {

    @Test
    @DisplayName("보드 내부 특정 색상 말의 총 점수를 계산한다.")
    void calculateScore() {
        Board board = new Board(new BlankBoard().fillWith(Map.of(
                // White
                new Position(1, 1), new Rook(Color.WHITE), // 5점
                new Position(2, 1), new Knight(Color.WHITE), // 2.5점
                new Position(3, 1), new Bishop(Color.WHITE), // 3점
                new Position(1, 2), new WhiteFirstPawn(), // 1점
                // Black
                new Position(4, 8), new Queen(Color.BLACK), // 9점
                new Position(5, 8), new King(Color.BLACK), // 0점
                new Position(1, 7), new BlackFirstPawn() // 1점
        )));
        ScoreManager scoreManager = new ScoreManager(board);

        assertAll(
                () -> assertThat(scoreManager.calculateScore(Color.WHITE)).isEqualTo(new Score(11.5)),
                () -> assertThat(scoreManager.calculateScore(Color.BLACK)).isEqualTo(new Score(10))
        );
    }

    @Test
    @DisplayName("한 파일 라인(세로 줄)에 폰이 두 개 이상 있으면 한 개 당 0.5점으로 계산한다.")
    void calculateScoreHasOverTwoPawn() {
        Board board = new Board(new BlankBoard().fillWith(Map.of(
                new Position(1, 2), new WhiteFirstPawn(),
                new Position(1, 3), new WhitePawn(),
                new Position(1, 5), new WhitePawn()
        )));
        ScoreManager scoreManager = new ScoreManager(board);

        assertThat(scoreManager.calculateScore(Color.WHITE)).isEqualTo(new Score(1.5));
    }

    @Test
    @DisplayName("한 파일 라인(세로 줄)에 폰이 한 개만 있으면 1점으로 계산한다.")
    void calculateScoreHasOnePawn() {
        Board board = new Board(new BlankBoard().fillWith(Map.of(
                new Position(1, 2), new WhiteFirstPawn()
        )));
        ScoreManager scoreManager = new ScoreManager(board);

        assertThat(scoreManager.calculateScore(Color.WHITE)).isEqualTo(new Score(1));
    }
}
