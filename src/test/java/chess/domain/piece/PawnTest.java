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

class PawnTest {

    @DisplayName("화이트 폰은 북쪽으로만 전진할 수 있다.")
    @Test
    void blackPawnCanMoveToNorth() {
        Pawn pawn = Pawn.WHITE_PAWN;
        Position currentPosition = Position.of(File.H, Rank.THREE);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(Set.of(Position.of(File.H, Rank.FOUR)));
    }

    @DisplayName("블랙 폰은 남쪽으로만 전진할 수 있다.")
    @Test
    void whitePawnCanMoveToSouth() {
        Pawn pawn = Pawn.BLACK_PAWN;
        Position currentPosition = Position.of(File.H, Rank.THREE);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(Set.of(Position.of(File.H, Rank.TWO)));
    }

    @DisplayName("화이트 폰은 초기 위치에서는 북쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStep() {
        Pawn pawn = Pawn.WHITE_PAWN;
        Position currentPosition = Position.of(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.H, Rank.THREE), Position.of(File.H, Rank.FOUR)));
    }

    @DisplayName("블랙 폰은 초기 위치에서는 남쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStepBlack() {
        Pawn pawn = Pawn.BLACK_PAWN;
        Position currentPosition = Position.of(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.H, Rank.SIX), Position.of(File.H, Rank.FIVE)));
    }

    @DisplayName("화이트 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForward() {
        Pawn pawn = Pawn.WHITE_PAWN;
        Position currentPosition = Position.of(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.of(File.H, Rank.THREE), Pawn.BLACK_PAWN,
                Position.of(File.G, Rank.THREE), Pawn.BLACK_PAWN
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.THREE)));
    }

    @DisplayName("블랙 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForwardBlack() {
        Pawn pawn = Pawn.BLACK_PAWN;
        Position currentPosition = Position.of(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.of(File.H, Rank.SIX), Pawn.WHITE_PAWN,
                Position.of(File.G, Rank.SIX), Pawn.WHITE_PAWN
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.G, Rank.SIX)));
    }


    @DisplayName("화이트 폰은 북쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExists() {
        Pawn pawn = Pawn.WHITE_PAWN;
        Position currentPosition = Position.of(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.of(File.G, Rank.THREE), Pawn.BLACK_PAWN
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(Set.of(Position.of(File.H, Rank.THREE),
                Position.of(File.H, Rank.FOUR), Position.of(File.G, Rank.THREE))
        );
    }

    @DisplayName("블랙 폰은 남쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExistsBlack() {
        Pawn pawn = Pawn.BLACK_PAWN;
        Position currentPosition = Position.of(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.of(File.G, Rank.SIX), Pawn.WHITE_PAWN
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition,
                new Board(board, new ScoreCalculator()));

        assertThat(movablePositions).isEqualTo(Set.of(Position.of(File.H, Rank.SIX),
                Position.of(File.H, Rank.FIVE), Position.of(File.G, Rank.SIX))
        );
    }

    @DisplayName("킹인지 물어보면 false를 반환한다")
    @Test
    void givenPawnWhenIsKingThenReturnFalse() {
        Pawn pawn = Pawn.BLACK_PAWN;

        assertThat(pawn.isKing()).isFalse();
    }

    @DisplayName("폰의 점수를 가져오면 1.0점을 반환한다")
    @Test
    void givenPawnWhenGetScoreThenReturn1() {
        Pawn pawn = Pawn.BLACK_PAWN;

        assertThat(pawn.getScore()).isEqualTo(1.0);
    }

    @DisplayName("폰에게 폰인지 물어보면 true를 반환한다")
    @Test
    void givenPawnWhenIsPawnThenReturnTrue() {
        Pawn pawn = Pawn.BLACK_PAWN;

        assertThat(pawn.isPawn()).isTrue();
    }
}
