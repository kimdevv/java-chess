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

class KnightTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                Position.of(File.A, Rank.FIVE), Pawn.WHITE_PAWN,
                Position.of(File.B, Rank.SIX), Queen.WHITE,
                Position.of(File.C, Rank.THREE), Queen.WHITE
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.C, Rank.FIVE),
                        Position.of(File.B, Rank.TWO)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                Position.of(File.A, Rank.FIVE), Pawn.BLACK_PAWN,
                Position.of(File.B, Rank.SIX), Queen.WHITE,
                Position.of(File.C, Rank.THREE), Queen.BLACK
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.C, Rank.FIVE),
                        Position.of(File.B, Rank.TWO),
                        Position.of(File.C, Rank.THREE)));
    }

    @DisplayName("킹인지 물어보면 false를 반환한다")
    @Test
    void givenKnightWhenIsKingThenReturnFalse() {
        Knight knight = Knight.WHITE;

        assertThat(knight.isKing()).isFalse();
    }

    @DisplayName("나이트의 점수를 가져오면 2.5점을 반환한다")
    @Test
    void givenKnightWhenGetScoreThenReturn2_5() {
        Knight knight = Knight.WHITE;

        assertThat(knight.getScore()).isEqualTo(2.5);
    }

    @DisplayName("나이트에게 폰인지 물어보면 false를 반환한다")
    @Test
    void givenKnightWhenIsPawnThenReturnFalse() {
        Knight knight = Knight.WHITE;

        assertThat(knight.isPawn()).isFalse();
    }
}
