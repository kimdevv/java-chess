package chess.domain.direction;

import static chess.domain.Fixtures.B3;
import static chess.domain.Fixtures.B5;
import static chess.domain.Fixtures.C2;
import static chess.domain.Fixtures.C6;
import static chess.domain.Fixtures.D4;
import static chess.domain.Fixtures.E2;
import static chess.domain.Fixtures.E6;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static chess.domain.Fixtures.F3;
import static chess.domain.Fixtures.F5;
import static chess.domain.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KnightDirectionTest {

    private static final Position SOURCE = D4;

    static Stream<Position> possibleTarget() {
        return Stream.of(
                E6, E2, C6, C2, F5, F3, B5, B3
        );
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_KnightDirection_When_CanReachWithReachablePosition_Then_True(Position target) {
        //given
        KnightDirection direction = new KnightDirection();
        //when, then
        assertThat(direction.canReach(SOURCE, target, EMPTY_OBSTACLE)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 도착 위치까지 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_KnightDirection_When_canReachWithUnreachablePosition_Then_False() {
        //given
        KnightDirection direction = new KnightDirection();
        //when, then
        assertThat(direction.canReach(SOURCE, H8, EMPTY_OBSTACLE)).isFalse();
    }
}
