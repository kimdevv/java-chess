package chess.domain.board;

import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드에서 체스 말을 이동시킬 수 있다.")
    void movePiece() {
        Board board = new Board(new ClearBoardInitializer());
        board.tryMove(Position.of(1, 7), Position.of(1, 5));
        Map<Position, Piece> boardPieces = board.getBoard();
        assertAll(
                () -> assertThat(boardPieces.get(Position.of(1, 7)))
                        .isEqualTo(Empty.getInstance()),
                () -> assertThat(boardPieces.get(Position.of(1, 5)))
                        .isEqualTo(BLACK_PAWN.getPiece()));
    }

    @Test
    @DisplayName("보드에서 체스 말을 이동할 수 없는 경우 거짓을 발생한다.")
    void movePieceThrowException() {
        Board board = new Board(new ClearBoardInitializer());
        assertThat(board.tryMove(Position.of(1, 7), Position.of(1, 2))).isFalse();
    }


    @Test
    @DisplayName("움직이려는 말의 진영 차례가 아닌 경우 예외가 발생한다.")
    void isTurnThrowException() {
        Board board = new Board(new ClearBoardInitializer());
        assertThat(board.tryMove(Position.of(1, 7), Position.of(1, 2))).isFalse();

    }

    @Test
    @DisplayName("King이 잡힐 시에 게임이 종료된다.")
    void isNotFinish() {
        Board board = new Board(new ClearBoardInitializer());
        assertThat(board.isKingKilled()).isFalse();
    }

    @Test
    @DisplayName("King이 잡힐 시에 게임이 종료된다.")
    void isFinish() {
        Board board = new Board(new ClearBoardInitializer());

        // RNBQqB.R <- king이 죽는 기보
        // PPPP..PP
        // .....P.N
        // ....P...
        // ........
        // ....p...
        // pppp.ppp
        // rnb.kbnr

        board.tryMove(Position.of(6, 7), Position.of(6, 6));
        board.tryMove(Position.of(5, 2), Position.of(5, 3));
        board.tryMove(Position.of(7, 8), Position.of(8, 6));
        board.tryMove(Position.of(4, 1), Position.of(8, 5));
        board.tryMove(Position.of(5, 7), Position.of(5, 5));
        board.tryMove(Position.of(8, 5), Position.of(5, 8));

        assertThat(board.isKingKilled()).isTrue();
    }
}
