package chess.domain.direction;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.C1;
import static chess.domain.Fixtures.C4;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinationDirectionTest {

    @Test
    @DisplayName("방향을 조합하여 이동이 가능한 경우 참을 반환한다.")
    void Given_CombinationDirectionWithUpDirectionLeftDirection_When_CanReachWithReachablePosition_Then_True() {
        //given
        UpDirection upDirection = new UpDirection(8);
        LeftDirection leftDirection = new LeftDirection(8);
        CombinationDirection combinationDirection = new CombinationDirection(upDirection, leftDirection);
        //when, then
        assertAll(
                () -> assertThat(combinationDirection.canReach(C1, C4, EMPTY_OBSTACLE)).isTrue(),
                () -> assertThat(combinationDirection.canReach(C1, A1, EMPTY_OBSTACLE)).isTrue()
        );
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_CombinationDirectionWithUpDirectionLeftDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        UpDirection upDirection = new UpDirection(8);
        LeftDirection leftDirection = new LeftDirection(8);
        CombinationDirection combinationDirection = new CombinationDirection(upDirection, leftDirection);
        //when, then
        assertAll(
                () -> assertThat(combinationDirection.canReach(C4, C1, EMPTY_OBSTACLE)).isFalse(),
                () -> assertThat(combinationDirection.canReach(A1, C1, EMPTY_OBSTACLE)).isFalse()
        );
    }
}
