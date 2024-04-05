package domain.chessGame;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardInitializer;
import domain.coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.chessGame.ChessGameStateRunning;
import state.chessGame.base.ChessGameState;

class ChessGameStateRunningTest {

    @DisplayName("움직임을 시작한 후 King을 잡지 못한 경우 상태는 지속된다.")
    @Test
    void moveChessGameAtRunning() {
        ChessGameStateRunning chessGameRunning = new ChessGameStateRunning(
                new ChessBoard(ChessBoardInitializer.createInitialBoard()), 0L, "WHITE");

        Coordinate start = Coordinate.from("a2");
        Coordinate destination = Coordinate.from("a4");

        ChessGameState chessGameState = chessGameRunning.move(start, destination);

        Assertions.assertThat(chessGameState.isPlaying()).isTrue();
    }

    @DisplayName("움직임을 시작한 후 end 커맨드를 입력하는 경우 playing 상태가 아니다.")
    @Test
    void endChessGameAtRunning() {
        ChessGameStateRunning chessGameRunning = new ChessGameStateRunning(
                new ChessBoard(ChessBoardInitializer.createInitialBoard()), 0L, "WHITE");

        ChessGameState chessGameState = chessGameRunning.end();

        Assertions.assertThat(chessGameState.isPlaying()).isFalse();
    }

    @DisplayName("움직임을 시작한 후 내 말이 아닌 말을 고를 경우 에러를 발생시킨다.")
    @Test
    void moveOtherColorPieceAtRunning() {
        ChessGameStateRunning chessGameRunning = new ChessGameStateRunning(
                new ChessBoard(ChessBoardInitializer.createInitialBoard()), 0L, "WHITE");

        Coordinate start = Coordinate.from("a7");
        Coordinate destination = Coordinate.from("a5");

        Assertions.assertThatThrownBy(() -> chessGameRunning.move(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 말을 선택 했습니다.");
    }

    @DisplayName("움직임을 시작한 후 내 말을 공격할 경우 에러를 발생시킨다.")
    @Test
    void attackSameColorPieceAtRunning() {
        ChessGameStateRunning chessGameRunning = new ChessGameStateRunning(
                new ChessBoard(ChessBoardInitializer.createInitialBoard()), 0L, "WHITE");

        Coordinate start = Coordinate.from("a2");
        Coordinate destination = Coordinate.from("b2");

        Assertions.assertThatThrownBy(() -> chessGameRunning.move(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 말을 선택 했습니다.");
    }

    @DisplayName("왕을 잡을 경우 Play 상태가 멈춘다.")
    @Test
    void attackKingAtRunning() {
        ChessGameStateRunning chessGameRunning = new ChessGameStateRunning(
                new ChessBoard(ChessBoardInitializer.createInitialBoard()), 0L, "WHITE");

        Coordinate start = Coordinate.from("e2");
        Coordinate destination = Coordinate.from("e3");
        chessGameRunning.move(start, destination);

        Coordinate start2 = Coordinate.from("f7");
        Coordinate destination2 = Coordinate.from("f6");
        chessGameRunning.move(start2, destination2);

        Coordinate start3 = Coordinate.from("d1");
        Coordinate destination3 = Coordinate.from("h5");
        chessGameRunning.move(start3, destination3);

        Coordinate start4 = Coordinate.from("g8");
        Coordinate destination4 = Coordinate.from("h6");
        chessGameRunning.move(start4, destination4);

        Coordinate start5 = Coordinate.from("h5");
        Coordinate destination5 = Coordinate.from("e8");
        ChessGameState chessGameState = chessGameRunning.move(start5, destination5);

        Assertions.assertThat(chessGameState.isPlaying()).isFalse();
    }
}
