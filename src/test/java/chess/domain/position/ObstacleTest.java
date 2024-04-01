package chess.domain.position;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.B2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObstacleTest {

    @Test
    @DisplayName("주어진 위치 정보에서 현재 위치가 도착지가 아니면 참을 반환한다.")
    void Given_Obstacle_When_IsBlockedCurrentPosition_Than_True() {
        //given
        Obstacle obstacle = new Obstacle(List.of(A1, B2));
        //when, then
        assertThat(obstacle.isBlocked(A1, B2)).isTrue();
    }

    @Test
    @DisplayName("현재 위치가 도착지이고 등록된 장애물이면 참을 반환한다.")
    void Given_Obstacle_When_IsBlockedTargetPosition_Than_False() {
        //given
        Obstacle obstacle = new Obstacle(List.of(A1, B2));
        //when, then
        assertThat(obstacle.isBlocked(B2, B2)).isFalse();
    }
}
