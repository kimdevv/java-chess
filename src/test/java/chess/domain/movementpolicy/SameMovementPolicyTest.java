package chess.domain.movementpolicy;

import static chess.domain.Fixtures.B1;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.direction.DownDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SameMovementPolicyTest {

    @Test
    @DisplayName("공격 방향 정책과 이동 방향 정책을 같도록 설정할 수 있다.")
    void Given_SameMovementPolicyWithDirection_When_canAttack_canMove_Then_Same() {
        //given
        SameMovementPolicy differentPolicy = new SameMovementPolicy(new DownDirection(1));

        //when, then
        assertThat(differentPolicy.canAttack(B1, B2, EMPTY_OBSTACLE))
                .isEqualTo(differentPolicy.canMove(B1, B2, EMPTY_OBSTACLE));
    }
}
