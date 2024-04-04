package domain.direction;

import static domain.direction.DiagonalDirection.getDirection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiagonalDirectionTest {

    // 음수이면 위로, 양수이면 아래로 이동한 것이다.
    int rowDifference;
    // 음수이면 왼쪽, 양수이면 오른쪽으로 이동한 것이다.
    int columnDifference;

    @DisplayName("이동이 없으면 예외가 발생한다.")
    @Test
    void dontMove() {
        rowDifference = 0;
        columnDifference = 0;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("위로만 이동하면 예외가 발생한다.")
    @Test
    void moveUp() {
        rowDifference = -1;
        columnDifference = 0;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("아래로만 이동하면 예외가 발생한다.")
    @Test
    void moveDown() {
        rowDifference = 1;
        columnDifference = 0;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("왼쪽으로만 이동하면 예외가 발생한다.")
    @Test
    void moveLeft() {
        rowDifference = 0;
        columnDifference = -1;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("오른쪽으로만 이동하면 예외가 발생한다.")
    @Test
    void moveRight() {
        rowDifference = 0;
        columnDifference = 1;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("상,하 방향으로의 이동 횟수보다 좌,우 방향으로의 이동 횟수가 많으면 예외가 발생한다.")
    @Test
    void moveMoreHorizonThanVertical() {
        rowDifference = 1;
        columnDifference = 2;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("좌,우 방향으로의 이동 횟수보다 상,하 방향으로의 이동 횟수가 많으면 예외가 발생한다.")
    @Test
    void moveMoreVerticalThanHorizon() {
        rowDifference = 2;
        columnDifference = -1;

        runExceptionTest(rowDifference, columnDifference);
    }

    @DisplayName("오른쪽 위 대각선 방향을 얻고, 이동 횟수를 계산한다.")
    @Test
    void calculateDistanceAfterGetUpRightDiagonal() {
        rowDifference = -3;
        columnDifference = 3;

        DiagonalDirection actual = DiagonalDirection.getDirection(rowDifference, columnDifference);
        DiagonalDirection expected = DiagonalDirection.UP_RIGHT;

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.calculateDistance(rowDifference, columnDifference)).isEqualTo(3);
    }

    @DisplayName("왼쪽 위 대각선 방향을 얻고, 이동 횟수를 계산한다.")
    @Test
    void calculateDistanceAfterGetUpLeftDiagonal() {
        rowDifference = -3;
        columnDifference = -3;

        DiagonalDirection actual = DiagonalDirection.getDirection(rowDifference, columnDifference);
        DiagonalDirection expected = DiagonalDirection.UP_LEFT;

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.calculateDistance(rowDifference, columnDifference)).isEqualTo(3);
    }

    @DisplayName("오른쪽 아래 대각선 방향을 얻고, 이동 횟수를 계산한다.")
    @Test
    void calculateDistanceAfterGetDownRightDiagonal() {
        rowDifference = 3;
        columnDifference = 3;

        DiagonalDirection actual = DiagonalDirection.getDirection(rowDifference, columnDifference);
        DiagonalDirection expected = DiagonalDirection.DOWN_RIGHT;

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.calculateDistance(rowDifference, columnDifference)).isEqualTo(3);
    }

    @DisplayName("왼쪽 아래 대각선 방향을 얻고, 이동 횟수를 계산한다.")
    @Test
    void calculateDistanceAfterGetDownLeftDiagonal() {
        rowDifference = 3;
        columnDifference = -3;

        DiagonalDirection actual = DiagonalDirection.getDirection(rowDifference, columnDifference);
        DiagonalDirection expected = DiagonalDirection.DOWN_LEFT;

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.calculateDistance(rowDifference, columnDifference)).isEqualTo(3);
    }

    void runExceptionTest(int rowDifference, int columnDifference) {
        assertThatThrownBy(() -> getDirection(rowDifference, columnDifference))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}
