package chess.domain.piece;

import chess.domain.board.BoardFactory;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    private Queen queen = new Queen(Team.WHITE);

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovablePoint1() {
        boolean result = queen.isMovablePoint(
                포인트("A1"),
                포인트("A8"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovablePoint2() {
        boolean result = queen.isMovablePoint(
                포인트("A8"),
                포인트("A1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovablePoint3() {
        boolean result = queen.isMovablePoint(
                포인트("H1"),
                포인트("A1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovablePoint4() {
        boolean result = queen.isMovablePoint(
                포인트("A1"),
                포인트("H1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 위로 움직일 수 있다.")
    void isMovablePoint5() {
        boolean result = queen.isMovablePoint(
                포인트("A1"),
                포인트("C3"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 움직일 수 있다.")
    void isMovablePoint6() {
        boolean result = queen.isMovablePoint(
                포인트("C4"),
                포인트("E2"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 움직일 수 있다.")
    void isMovablePoint7() {
        boolean result = queen.isMovablePoint(
                포인트("H1"),
                포인트("A8"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 움직일 수 있다.")
    void isMovablePoint8() {
        boolean result = queen.isMovablePoint(
                포인트("H8"),
                포인트("B2"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선 또는 직선이 아니라면 움직일 수 없다.")
    void invalidMovablePoint1() {
        boolean result = queen.isMovablePoint(
                포인트("H8"),
                포인트("B6"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("같은 위치로 움직일 수 없다.")
    void invalidMovablePoint2() {
        boolean result = queen.isMovablePoint(
                포인트("B6"),
                포인트("B6"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착 지점에 같은 팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove1() {
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A1"), queen);
        board.put(포인트("B2"), new King(Team.WHITE));

        boolean result = queen.canMove(
                포인트("A1"),
                포인트("B2"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 움직일 수 없다.")
    void canNotMove2() {
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A1"), queen);
        board.put(포인트("F6"), new King(Team.BLACK));

        boolean result = queen.canMove(
                포인트("A1"),
                포인트("H8"),
                board);

        assertThat(result).isFalse();
    }
}
