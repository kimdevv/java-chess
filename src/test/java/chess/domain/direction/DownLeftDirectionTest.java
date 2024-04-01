package chess.domain.direction;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.A2;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static chess.domain.Fixtures.G7;
import static chess.domain.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Obstacle;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DownLeftDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithReachablePosition_Then_True() {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        //when, then
        assertThat(direction.canReach(H8, A1, EMPTY_OBSTACLE)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        //when, then
        assertThat(direction.canReach(A2, B2, EMPTY_OBSTACLE)).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        DownLeftDirection direction = new DownLeftDirection(8);
        //when, then
        assertThat(direction.canReach(H8, A1, new Obstacle(List.of(G7)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_DownLeftDirection_When_CanReachWithUnreachableMoveCount_Then_False() {
        //given
        DownLeftDirection direction = new DownLeftDirection(1);
        //when, then
        assertThat(direction.canReach(H8, A1, EMPTY_OBSTACLE)).isFalse();
    }
}
