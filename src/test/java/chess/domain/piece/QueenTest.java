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

class QueenTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetTeamMThenStop() {
        Queen queen = Queen.WHITE;
        Position currentQueenPosition = Position.of(File.G, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                Position.of(File.E, Rank.SEVEN), Pawn.WHITE_PAWN,
                Position.of(File.F, Rank.SIX), Pawn.WHITE_PAWN,
                Position.of(File.G, Rank.FIVE), Pawn.WHITE_PAWN
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.SIX),
                        Position.of(File.G, Rank.EIGHT),
                        Position.of(File.H, Rank.SIX),
                        Position.of(File.H, Rank.SEVEN),
                        Position.of(File.H, Rank.EIGHT),
                        Position.of(File.F, Rank.EIGHT),
                        Position.of(File.F, Rank.SEVEN)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Queen queen = Queen.WHITE;
        Position currentQueenPosition = Position.of(File.G, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                Position.of(File.E, Rank.SEVEN), Pawn.BLACK_PAWN,
                Position.of(File.F, Rank.SIX), Pawn.BLACK_PAWN,
                Position.of(File.G, Rank.FIVE), Pawn.BLACK_PAWN
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.SIX),
                        Position.of(File.G, Rank.EIGHT),
                        Position.of(File.H, Rank.SIX),
                        Position.of(File.H, Rank.SEVEN),
                        Position.of(File.H, Rank.EIGHT),
                        Position.of(File.F, Rank.EIGHT),
                        Position.of(File.F, Rank.SEVEN),
                        Position.of(File.E, Rank.SEVEN),
                        Position.of(File.F, Rank.SIX),
                        Position.of(File.G, Rank.FIVE)));
    }

    @DisplayName("킹인지 물어보면 false를 반환한다")
    @Test
    void givenQueenWhenIsKingThenReturnFalse() {
        Queen queen = Queen.WHITE;

        assertThat(queen.isKing()).isFalse();
    }

    @DisplayName("퀸의 점수를 가져오면 9.0점을 반환한다")
    @Test
    void givenQueenWhenGetScoreThenReturn9() {
        Queen queen = Queen.BLACK;

        assertThat(queen.getScore()).isEqualTo(9.0);
    }

    @DisplayName("퀸에게 폰인지 물어보면 false를 반환한다")
    @Test
    void givenQueenWhenIsPawnThenReturnFalse() {
        Queen queen = Queen.BLACK;

        assertThat(queen.isPawn()).isFalse();
    }
}
