package domain.piece;

import static domain.PositionFixture.C_FIVE;
import static domain.PositionFixture.C_FOUR;
import static domain.PositionFixture.C_THREE;
import static domain.PositionFixture.D_FIVE;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.D_THREE;
import static domain.PositionFixture.E_FIVE;
import static domain.PositionFixture.E_FOUR;
import static domain.PositionFixture.E_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    /*
      ........  7
      ........  6
      ..812...  5
      ..7K3...  4
      ..654...  3
      ........  2
      ........  1 (rank 1)

      abcdefgh
   */
    @Test
    @DisplayName("킹은 인접한 8방향으로 움직일 수 있다")
    void isReachable() {
        final Piece king = new King(Color.BLACK);

        assertAll(
                () -> assertThat(king.isReachable(new Vector(D_FOUR, D_FIVE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, E_FIVE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, E_FOUR), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, E_THREE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, D_THREE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, C_THREE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, C_FOUR), Empty.INSTANCE)).isTrue(),
                () -> assertThat(king.isReachable(new Vector(D_FOUR, C_FIVE), Empty.INSTANCE)).isTrue()
        );
    }
}
