package chess.domain.game;

import chess.domain.board.BoardFactory;
import chess.domain.piece.*;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.fixture.PointFixture.포인트;
import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("현재 턴의 플레이어가 기물을 움직인다.")
    void move() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();

        Point departure = Point.of('b', 2);
        Point destination = Point.of('b', 3);
        chessGame.move(departure, destination);

        assertThat(board.get(departure)).isEqualTo(Empty.INSTANCE);
        assertThat(board.get(destination)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("King 이 잡히면 게임을 더 진행할 수 없다.")
    void canPlay() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        board.put(포인트("D7"), new Bishop(Team.WHITE));
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();
        chessGame.move(포인트("D7"), 포인트("E8"));

        boolean result = chessGame.isPlayable();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("현재 남아 있는 말에 점수를 계산할 수 있다.")
    void calculateScore1() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();

        double score = chessGame.calculateScore(Team.WHITE);

        assertThat(score).isEqualTo(38.0);
    }

    @Test
    @DisplayName("Pawn이 같은 세로 줄에 있으면 0.5점으로 계산된다.")
    void calculateScore2() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        board.put(포인트("A3"), new Pawn(Team.WHITE));
        board.put(포인트("A4"), new Pawn(Team.WHITE));
        board.put(포인트("A5"), new Pawn(Team.WHITE));
        board.put(포인트("B2"), Empty.INSTANCE);
        board.put(포인트("C2"), Empty.INSTANCE);
        board.put(포인트("D2"), Empty.INSTANCE);
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();

        double score = chessGame.calculateScore(Team.WHITE);

        assertThat(score).isEqualTo(36.0);
    }
}
