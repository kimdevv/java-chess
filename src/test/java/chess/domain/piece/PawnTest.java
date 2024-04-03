package chess.domain.piece;

import chess.domain.board.BoardFactory;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.fixture.PawnFixture.검정_폰;
import static chess.fixture.PawnFixture.흰색_폰;
import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("흰색 폰은 위로 한 칸 이동할 수 있다.")
    void isMovablePoint1_white() {
        Pawn pawn = 흰색_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A3"),
                포인트("A4"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검정 폰은 아래로 한 칸 이동할 수 있다.")
    void isMovablePoint1_black() {
        Pawn pawn = 검정_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A7"),
                포인트("A6"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("흰색 폰은 처음 움직일 땐 위로 두 칸 이동할 수 있다.")
    void isMovablePoint2_white() {
        Pawn pawn = 흰색_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A2"),
                포인트("A4"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검정 폰은 처음 움직일 땐 아래로 두 칸 이동할 수 있다.")
    void isMovablePoint2_black() {
        Pawn pawn = 검정_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A7"),
                포인트("A5"));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 아래로 갈 수 없다.")
    void invalidIsMovablePoint1_white() {
        Pawn pawn = 흰색_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A2"),
                포인트("A1"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검정색 폰은 위로 갈 수 없다.")
    void invalidIsMovablePoint1_black() {
        Pawn pawn = 검정_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A7"),
                포인트("A8"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("초기 위치가 아닌 흰색 폰은 두 칸 위로 움직일 수 없다")
    void invalidIsMovablePoint2_white() {
        Pawn pawn = 흰색_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A3"),
                포인트("A5"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("초기 위치가 아닌 검정 폰은 두 칸 아래로 움직일 수 없다")
    void invalidIsMovablePoint2_black() {
        Pawn pawn = 검정_폰();

        boolean result = pawn.isMovablePoint(
                포인트("A6"),
                포인트("A4"));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선에 상대 팀의 기물이 있으면 대각선으로 움직일 수 있다.")
    void canMove_white() {
        Pawn pawn = 흰색_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A2"), pawn);
        board.put(포인트("B3"), new Knight(Team.BLACK));

        boolean result = pawn.canMove(
                포인트("A2"),
                포인트("B3"),
                board);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선에 상대 팀의 기물이 있으면 대각선으로 움직일 수 있다.")
    void canMove_black() {
        Pawn pawn = 검정_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A7"), pawn);
        board.put(포인트("B6"), new Knight(Team.WHITE));

        boolean result = pawn.canMove(
                포인트("A7"),
                포인트("B6"),
                board);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("도착 지점에 같은 팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove1_white() {
        Pawn pawn = 흰색_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A2"), pawn);
        board.put(포인트("A4"), new King(Team.WHITE));

        boolean result = pawn.canMove(
                포인트("A2"),
                포인트("A4"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착 지점에 같은 팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove1_black() {
        Pawn pawn = 검정_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A7"), pawn);
        board.put(포인트("A5"), new King(Team.BLACK));

        boolean result = pawn.canMove(
                포인트("A7"),
                포인트("A5"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착 지점에 같은 상대팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove2_white() {
        Pawn pawn = 흰색_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A2"), pawn);
        board.put(포인트("A4"), new King(Team.BLACK));

        boolean result = pawn.canMove(
                포인트("A2"),
                포인트("A4"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착 지점에 같은 상대팀의 기물이 있으면 움직일 수 없다.")
    void canNotMove2_black() {
        Pawn pawn = 검정_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A7"), pawn);
        board.put(포인트("A5"), new King(Team.WHITE));

        boolean result = pawn.canMove(
                포인트("A7"),
                포인트("A5"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("처음 두 칸 이동할 때 이동 경로에 기물이 있으면 움직일 수 없다.")
    void canNotMove3_white() {
        Pawn pawn = 흰색_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A2"), pawn);
        board.put(포인트("A3"), new King(Team.WHITE));

        boolean result = pawn.canMove(
                포인트("A2"),
                포인트("A4"),
                board);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("처음 두 칸 이동할 때 이동 경로에 기물이 있으면 움직일 수 없다.")
    void canNotMove3_black() {
        Pawn pawn = 검정_폰();
        Map<Point, Piece> board = BoardFactory.createEmptyBoard();
        board.put(포인트("A7"), pawn);
        board.put(포인트("A6"), new King(Team.WHITE));

        boolean result = pawn.canMove(
                포인트("A7"),
                포인트("A5"),
                board);

        assertThat(result).isFalse();
    }
}
