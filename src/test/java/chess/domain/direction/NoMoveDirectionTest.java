package chess.domain.direction;

import static chess.domain.Fixtures.C1;
import static chess.domain.Fixtures.C4;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoMoveDirectionTest {

    @Test
    @DisplayName("출발지와 도착지가 어떠한 경우에도 도달할 수 없다")
    void Given_NotMoveDirection_When_WhateverSourceTarget_Then_False() {
        //given
        NoMoveDirection direction = new NoMoveDirection();
        //when, then
        assertThat(direction.canReach(C4, C1, EMPTY_OBSTACLE)).isFalse();
    }
}
