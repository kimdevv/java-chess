package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Map<Point, Piece> tempBoard;

    @BeforeEach
    void setUp() {
        tempBoard = BoardFactory.createEmptyBoard();
    }

    @Test
    @DisplayName("비숍을 a1을 a3으로 이동시킬 수 있다.")
    void move1() {
        tempBoard.put(포인트("A1"), new Bishop(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(Team.WHITE,
                포인트("A1"),
                포인트("C3"));

        assertThat(board.getBoard().get(포인트("C3"))).isEqualTo(new Bishop(Team.WHITE));
        assertThat(board.getBoard().get(포인트("A1"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("나이트를 a1에서 c2로 이동시킬 수 있다.")
    void move2() {
        tempBoard.put(포인트("A1"), new Knight(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(Team.WHITE,
                포인트("A1"),
                포인트("C2"));

        assertThat(board.getBoard().get(포인트("C2"))).isEqualTo(new Knight(Team.WHITE));
        assertThat(board.getBoard().get(포인트("A1"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("룩을 a1을 a8으로 이동시킬 수 있다.")
    void move3() {
        tempBoard.put(포인트("A1"), new Rook(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(Team.WHITE,
                포인트("A1"),
                포인트("A8"));

        assertThat(board.getBoard().get(포인트("A8"))).isEqualTo(new Rook(Team.WHITE));
        assertThat(board.getBoard().get(포인트("A1"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("폰을 a2에서 a3으로 이동시킬 수 있다.")
    void move4() {
        tempBoard.put(포인트("A2"), new Pawn(Team.WHITE));
        Board board = new Board(tempBoard);

        board.move(Team.WHITE,
                포인트("A2"),
                포인트("A3"));

        assertThat(board.getBoard().get(포인트("A3"))).isEqualTo(new Pawn(Team.WHITE));
        assertThat(board.getBoard().get(포인트("A2"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("킹을 h4에서 g3으로 이동시킬 수 있다.")
    void move5() {
        tempBoard.put(포인트("H4"), new King(Team.BLACK));
        Board board = new Board(tempBoard);

        board.move(Team.BLACK,
                포인트("H4"),
                포인트("G3"));

        assertThat(board.getBoard().get(포인트("G3"))).isEqualTo(new King(Team.BLACK));
        assertThat(board.getBoard().get(포인트("H4"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("퀸을 h3에서 f1으로 이동시킬 수 있다.")
    void move6() {
        tempBoard.put(포인트("H3"), new Queen(Team.BLACK));
        Board board = new Board(tempBoard);

        board.move(Team.BLACK,
                포인트("H3"),
                포인트("F1"));

        assertThat(board.getBoard().get(포인트("F1"))).isEqualTo(new Queen(Team.BLACK));
        assertThat(board.getBoard().get(포인트("H3"))).isEqualTo(Empty.INSTANCE);
    }

    @Test
    @DisplayName("비숍이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    void invalidMove() {
        tempBoard.put(포인트("A1"), new Bishop(Team.WHITE));
        tempBoard.put(포인트("B2"), new Bishop(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(Team.WHITE,
                포인트("A1"),
                포인트("B2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 위치가 아닙니다.");
    }

    @Test
    @DisplayName("룩이 이동할 경로에 기물이 있으면 예외가 발생한다.")
    void invalidMove2() {
        tempBoard.put(포인트("A1"), new Rook(Team.WHITE));
        tempBoard.put(포인트("A5"), new Rook(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(Team.WHITE,
                포인트("A1"),
                포인트("A8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 위치가 아닙니다.");
    }

    @Test
    @DisplayName("폰은 수직으로 이동할 때 적이 경로에 있어도 전진할 수 없다.")
    void invalidMove3() {
        tempBoard.put(포인트("A1"), new Pawn(Team.WHITE));
        tempBoard.put(포인트("A2"), new Pawn(Team.BLACK));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(Team.WHITE,
                포인트("A1"),
                포인트("A2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 위치가 아닙니다.");
    }

    @Test
    @DisplayName("폰은 대각선에 적이 없으면 대각선으로 이동할 수 없다.")
    void invalidMove4() {
        tempBoard.put(포인트("A1"), new Pawn(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(Team.WHITE,
                포인트("A1"),
                포인트("B2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 기물이 이동할 수 있는 위치가 아닙니다.");
    }

    @Test
    @DisplayName("상대방의 기물을 이동할 수 없다.")
    void invalidMove5() {
        tempBoard.put(포인트("A1"), new Pawn(Team.WHITE));
        Board board = new Board(tempBoard);

        assertThatThrownBy(() -> board.move(Team.BLACK,
                포인트("A1"),
                포인트("A2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대방의 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("왕이 죽었는지 확인할 수 있다.")
    void isKingDead_false() {
        Map<Point, Piece> chessBoard = BoardFactory.createInitialChessBoard();
        Board board = new Board(chessBoard);

        boolean result = board.isKingDead();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("검정색 왕이 죽었는지 확인할 수 있다.")
    void isBlackKingDead_true() {
        Map<Point, Piece> chessBoard = BoardFactory.createInitialChessBoard();
        chessBoard.put(포인트("E7"), new Rook(Team.WHITE));
        Board board = new Board(chessBoard);

        board.move(Team.WHITE,
                포인트("E7"),
                포인트("E8"));
        boolean result = board.isKingDead();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("흰색 왕이 죽었는지 확인할 수 있다.")
    void isWhiteKingDead_true() {
        Map<Point, Piece> chessBoard = BoardFactory.createInitialChessBoard();
        chessBoard.put(포인트("D2"), new Pawn(Team.BLACK));
        Board board = new Board(chessBoard);

        board.move(Team.BLACK,
                포인트("D2"),
                포인트("E1"));
        boolean result = board.isKingDead();

        assertThat(result).isTrue();
    }
}
