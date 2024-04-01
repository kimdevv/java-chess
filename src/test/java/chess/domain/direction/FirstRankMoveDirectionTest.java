package chess.domain.direction;

import static chess.domain.Fixtures.B1;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.B3;
import static chess.domain.Fixtures.B4;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstRankMoveDirectionTest {

    @Test
    @DisplayName("설정한 Rank에서 이동할 경우 이동이 가능하면 참을 반환한다.")
    void Given_FirstRankMoveDirectionWithStartRankAndDirection_When_CanReachWithStartRank_Then_True() {
        //given
        FirstRankMoveDirection firstRankMoveDirection = new FirstRankMoveDirection(new UpDirection(2), Rank.TWO);
        //when, then
        assertThat(firstRankMoveDirection.canReach(B2, B4, EMPTY_OBSTACLE)).isTrue();
    }

    @Test
    @DisplayName("설정한 Rank에서 이동하지 않을 경우 거짓을 반환한다.")
    void Given_FirstRankMoveDirectionWithStartRankAndDirection_When_CanReachWithNotStartRank_Then_False() {
        //given
        FirstRankMoveDirection firstRankMoveDirection = new FirstRankMoveDirection(new UpDirection(2), Rank.TWO);
        //when, then
        assertThat(firstRankMoveDirection.canReach(B1, B3, EMPTY_OBSTACLE)).isFalse();
    }
}
