package domain.direction;

import static domain.direction.KnightDirection.getDirection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightDirectionTest {

    @Nested
    @DisplayName("나이트의 방향을 얻을 수 없다.")
    class InvalidInput {

        @DisplayName("이동하지 않은 경우")
        @Test
        void getDirection_WhenSameCoordinate() {
            assertThatThrownBy(() -> getDirection(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,0", "-1,0"}, delimiter = ',')
        @DisplayName("상,하로만 이동한 경우")
        void getDirection_WhenOnlyUpOrDown(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"0,1", "0,-1"}, delimiter = ',')
        @DisplayName("좌,우로만 이동한 경우")
        void getDirection_WhenOnlyLeftOrRight(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,1", "-3,-3"}, delimiter = ',')
        @DisplayName("대각선으로 이동한 경우")
        void getDirection_WhenOtherCase(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("나이트의 이동 방향을 얻는다.")
    class GetKnightDirectionTest {

        Direction expected;

        @DisplayName("위로 한칸 이동 후, 왼쪽 위 대각선으로 이동한다.")
        @Test
        void upAndUpLeftDiagonal() {
            expected = KnightDirection.UP_LEFT;

            assertThat(getDirection(-2, -1)).isEqualTo(expected);
        }

        @DisplayName("위로 한칸 이동 후, 오른쪽 위 대각선으로 이동한다.")
        @Test
        void upAndUpRightDiagonal() {
            expected = KnightDirection.UP_RIGHT;

            assertThat(getDirection(-2, 1)).isEqualTo(expected);
        }

        @DisplayName("아래로 한칸 이동 후, 왼쪽 아래 대각선으로 이동한다.")
        @Test
        void downAndDownLeftDiagonal() {
            expected = KnightDirection.DOWN_LEFT;

            assertThat(getDirection(2, -1)).isEqualTo(expected);
        }

        @DisplayName("아래로 한칸 이동 후, 오른쪽 아래 대각선으로 이동한다.")
        @Test
        void downAndDownRightDiagonal() {
            expected = KnightDirection.DOWN_RIGHT;

            assertThat(getDirection(2, 1)).isEqualTo(expected);
        }

        @DisplayName("왼쪽으로 한칸 이동 후, 왼쪽 위 대각선으로 이동한다.")
        @Test
        void leftAndUpLeftDiagonal() {
            expected = KnightDirection.LEFt_UP;

            assertThat(getDirection(-1, -2)).isEqualTo(expected);
        }

        @DisplayName("왼쪽으로 한칸 이동 후, 왼쪽 아래 대각선으로 이동한다.")
        @Test
        void leftAndDownLeftDiagonal() {
            expected = KnightDirection.LEFT_DOWN;

            assertThat(getDirection(1, -2)).isEqualTo(expected);
        }

        @DisplayName("오른쪽으로 한칸 이동 후, 오른쪽 위 대각선으로 이동한다.")
        @Test
        void rightAndUpRightDiagonal() {
            expected = KnightDirection.RIGHT_UP;

            assertThat(getDirection(-1, 2)).isEqualTo(expected);
        }

        @DisplayName("오른쪽으로 한칸 이동 후, 오른쪽 아래 대각선으로 이동한다.")
        @Test
        void rightAndDownRightDiagonal() {
            expected = KnightDirection.RIGHT_DOWN;

            assertThat(getDirection(1, 2)).isEqualTo(expected);
        }
    }
}
