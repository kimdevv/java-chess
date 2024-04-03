package chess.domain.piece;

import chess.domain.board.BoardFactory;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    private Rook rook = new Rook(Team.WHITE);

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovablePoint1() {
        boolean result = rook.isMovablePoint(
                포인트("A1"),
                포인트("A8"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovablePoint2() {
        boolean result = rook.isMovablePoint(
                포인트("A8"),
                포인트("A1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovablePoint3() {
        boolean result = rook.isMovablePoint(
                포인트("H1"),
                포인트("A1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovablePoint4() {
        boolean result = rook.isMovablePoint(
                포인트("A1"),
                포인트("H1"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움질수 없다")
    void invalidMovablePoint1() {
        boolean result = rook.isMovablePoint(
                포인트("A1"),
                포인트("H8"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("같은 위치로 이동할 수 없다.")
    void invalidMovablePoint2() {
        boolean result = rook.isMovablePoint(
                포인트("A1"),
                포인트("A1"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착 지점에 같은 팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove1() {
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A1"), rook);
        board.put(포인트("A8"), new King(Team.WHITE));

        boolean result = rook.canMove(
                포인트("A1"),
                포인트("A8"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 움직일 수 없다.")
    void canNotMove2() {
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A1"), rook);
        board.put(포인트("A4"), new King(Team.BLACK));

        boolean result = rook.canMove(
                포인트("A1"),
                포인트("A8"),
                board);

        assertThat(result).isFalse();
    }
}
