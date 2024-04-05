package chess.domain.piece;

import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.WHITE_KING;
import static chess.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.E5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.fixture.MovePathFixture;
import chess.fixture.PositionFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WhitePawnTest {

    @DisplayName("흰색 폰은 특별한 경우가 아니면 한 칸 위로 움직인다.")
    @Nested
    class whitePawnMove {

        /*
        ........  8
        ........  7
        ........  6
        ...*....  5
        ...p....  4
        ........  3
        ........  2
        ........  1

        abcdefgh
         */
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D5));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D5), D4);
        }
    }

    @DisplayName("흰색 폰은 초기화 위치에서 한 칸 혹은 두 칸 위로 움직인다.")
    @Nested
    class whitePawnMoveAtInitialPosition {

        /*
        ........  8
        ........  7
        ........  6
        ........  5
        ...*....  4
        ...*....  3
        ...p....  2
        ........  1

        abcdefgh
         */
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D2, target, MovePathFixture.noPieces());

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(D3, D4));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D2, target, MovePathFixture.noPieces());

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(D3, D4), D2);
        }
    }

    @DisplayName("흰색 폰은 상대 편 기물이 존재하면 위 대각선 방향으로 움직인다. 한 칸 위로는 움직일 수 없다.")
    @Nested
    class whitePawnMoveWhenAttack {

        /*
        ........  8
        ........  7
        ........  6
        ..PPP...  5
        ...p....  4
        ........  3
        ........  2
        ........  1

        abcdefgh
         */
        @ParameterizedTest
        @MethodSource("movableTargets")
        void hasFollowedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(BLACK_PAWN));

            assertThat(actual).isTrue();
        }

        private static Stream<Arguments> movableTargets() {
            return PositionFixture.movablePositions(List.of(C5, E5));
        }

        @ParameterizedTest
        @MethodSource("immovableTargets")
        void hasViolatedRule(Position target) {
            boolean actual = WHITE_PAWN.hasFollowedRule(D4, target, MovePathFixture.hasTargetPiece(BLACK_PAWN));

            assertThat(actual).isFalse();
        }

        private static Stream<Arguments> immovableTargets() {
            return PositionFixture.immovablePositions(List.of(C5, E5), D4);
        }
    }

    @DisplayName("같은 색의 폰이 없는 경우 1점을 준다.")
    @Test
    void scoreWhenNoSameSidePawn() {
        List<Piece> pieces = List.of(
                BLACK_KING,
                BLACK_PAWN
        );

        double score = BLACK_PAWN.score(pieces);

        assertThat(score).isEqualTo(1);
    }

    @DisplayName("같은 색의 폰이 있는 경우 0.5점을 준다.")
    @Test
    void scoreWhenSameSidePawnExist() {
        List<Piece> pieces = List.of(
                WHITE_KNIGHT,
                WHITE_PAWN,
                WHITE_PAWN,
                WHITE_KING
        );

        double score = WHITE_PAWN.score(pieces);

        assertThat(score).isEqualTo(0.5);
    }
}
