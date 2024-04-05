package domain.chessGame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.chessGame.InitialChessGameState;

class InitialChessGameStateTest {

    @DisplayName("초기 상태에서 보드를 가져오려고 할 경우 에러를 발생한다.")
    @Test
    void getBoardAtInitialState() {
        InitialChessGameState initialChessGame = new InitialChessGameState();

        Assertions.assertThatThrownBy(initialChessGame::getBoard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아직 시작하지 않아 보드판을 가져올 수 없습니다.");
    }

    @DisplayName("초기 상태에서 상태를 보려고 할 경우 에러를 발생한다.")
    @Test
    void showAtInitialState() {
        InitialChessGameState initialChessGame = new InitialChessGameState();

        Assertions.assertThatThrownBy(initialChessGame::show)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아직 시작하지 않아 보여줄 상태가 없습니다.");
    }
}
