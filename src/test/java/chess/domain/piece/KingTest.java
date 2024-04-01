package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.ScoreCalculator;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        King king = King.WHITE;
        Position currentKingPosition = Position.of(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.of(File.F, Rank.TWO), Pawn.WHITE_PAWN,
                Position.of(File.D, Rank.ONE), Queen.WHITE);

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.D, Rank.TWO), Position.of(File.E, Rank.TWO),
                        Position.of(File.F, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        King king = King.WHITE;
        Position currentKingPosition = Position.of(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.of(File.F, Rank.TWO), Pawn.WHITE_PAWN,
                Position.of(File.D, Rank.ONE), Queen.WHITE,
                Position.of(File.D, Rank.TWO), Pawn.BLACK_PAWN);

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.D, Rank.TWO), Position.of(File.E, Rank.TWO),
                        Position.of(File.F, Rank.ONE)));
    }

    @DisplayName("킹인지 물어보면 true를 반환한다")
    @Test
    void givenKingWhenIsKingThenReturnTrue() {
        King king = King.WHITE;

        assertThat(king.isKing()).isTrue();
    }

    @DisplayName("킹의 점수를 가져오면 0점을 반환한다")
    @Test
    void givenKingWhenGetScoreThenReturn0() {
        King king = King.WHITE;

        assertThat(king.getScore()).isEqualTo(0);
    }

    @DisplayName("킹에게 폰인지 물어보면 false를 반환한다")
    @Test
    void givenKingWhenIsPawnThenReturnFalse() {
        King king = King.WHITE;

        assertThat(king.isPawn()).isFalse();
    }
}
