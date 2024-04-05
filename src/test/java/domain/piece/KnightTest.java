package domain.piece;

import static domain.PositionFixture.B_FIVE;
import static domain.PositionFixture.B_THREE;
import static domain.PositionFixture.C_SIX;
import static domain.PositionFixture.C_TWO;
import static domain.PositionFixture.D_FOUR;
import static domain.PositionFixture.E_SIX;
import static domain.PositionFixture.E_TWO;
import static domain.PositionFixture.F_FIVE;
import static domain.PositionFixture.F_THREE;
import static domain.piece.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.board.position.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    /*
          ........  7
          ..8.1...  6
          .7...2..  5
          ...N....  4
          .6...3..  3
          ..5.4...  2
          ........  1 (rank 1)

          abcdefgh
       */
    @Test
    @DisplayName("나이트는 (파일 1, 랭크 2) 혹은 (파일 2 , 랭크 1) 거리 만큼 움직일 수 있다")
    void isReachable() {
        final Piece knight = new Knight(BLACK);

        assertAll(
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, E_SIX), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, F_FIVE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, F_THREE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, E_TWO), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, C_TWO), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, B_THREE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, B_FIVE), Empty.INSTANCE)).isTrue(),
                () -> assertThat(knight.isReachable(new Vector(D_FOUR, C_SIX), Empty.INSTANCE)).isTrue()
        );
    }
}
