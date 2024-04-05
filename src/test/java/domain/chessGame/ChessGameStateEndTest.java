package domain.chessGame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.chessGame.ChessGameStateEnd;

class ChessGameStateEndTest {

    @DisplayName("체스 게임이 종료된 상태에서 보드를 가져오려고 할 경우 에러를 발생한다.")
    @Test
    void getBoardAtChessGameEnd() {
        ChessGameStateEnd chessGameEnd = new ChessGameStateEnd(0L);

        Assertions.assertThatThrownBy(chessGameEnd::getBoard)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("이미 종료한 상태의 게임으로 보드판을 불러올 수 없습니다.");
    }
}
