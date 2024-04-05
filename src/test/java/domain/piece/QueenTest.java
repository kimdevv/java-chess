package domain.piece;

import static domain.PositionFixture.A_FOUR;
import static domain.PositionFixture.A_ONE;
import static domain.PositionFixture.A_SEVEN;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_ONE;
import static domain.PositionFixture.D_SEVEN;
import static domain.PositionFixture.G_ONE;
import static domain.PositionFixture.G_SEVEN;
import static domain.PositionFixture.H_FOUR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    /*
          8..1..2.  7
          .x.x.x..  6
          ..xxx...  5
          7xxQxxx3  4
          ..xxx...  3
          .x.x.x..  2
          6..5..4.  1 (rank 1)

          abcdefgh
       */
    @Test
    @DisplayName("퀸은 가로, 세로, 대각선 방향으로 움직일 수 있다")
    void isDiagonal() {
        final Piece queen = new Queen(Color.BLACK);

        assertAll(
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, D_SEVEN), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, G_SEVEN), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, H_FOUR), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, G_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, D_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, A_ONE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, A_FOUR), Empty.INSTANCE)).isTrue(),
                () -> assertThat(queen.isReachable(new Vector(D_FOUR, A_SEVEN), Empty.INSTANCE)).isTrue()
        );
    }

}
