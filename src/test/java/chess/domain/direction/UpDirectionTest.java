package chess.domain.direction;

import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.A8;
import static chess.domain.Fixtures.C1;
import static chess.domain.Fixtures.C4;
import static chess.domain.Fixtures.C8;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static chess.domain.Fixtures.F1;
import static chess.domain.Fixtures.H1;
import static chess.domain.Fixtures.H3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Obstacle;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_UpDirection_When_CanReachWithReachablePosition_then_True() {
        //given
        UpDirection direction = new UpDirection(8);
        //when, then
        assertThat(direction.canReach(H1, H3, EMPTY_OBSTACLE)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        UpDirection direction = new UpDirection(8);
        //when, then
        assertThat(direction.canReach(A8, A7, EMPTY_OBSTACLE)).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        UpDirection direction = new UpDirection(8);
        //when, then
        assertThat(direction.canReach(C1, C8, new Obstacle(List.of(C4)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithUnreachableMoveCount_then_False() {
        //given
        UpDirection direction = new UpDirection(1);
        //when, then
        assertThat(direction.canReach(H1, F1, EMPTY_OBSTACLE)).isFalse();
    }
}

