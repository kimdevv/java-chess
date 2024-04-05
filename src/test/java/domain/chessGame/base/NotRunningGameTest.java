package domain.chessGame.base;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.chessGame.base.NotRunningGameState;

class NotRunningGameTest {

    private static class NotRunningTestImpl extends NotRunningGameState {

        @Override
        public boolean isKingCaught() {
            return false;
        }

        @Override
        public Map<Coordinate, ChessPiece> getBoard() {
            return null;
        }

        @Override
        public Long getGameId() {
            return null;
        }

        @Override
        public void show() {
        }
    }

    @DisplayName("움직일 수 없는 상태에서 움직이려 할 경우 에러를 발생시킨다.")
    @Test
    void moveWhenCanNotMoveStatus() {
        NotRunningTestImpl notRunningTest = new NotRunningTestImpl();
        Coordinate start = Coordinate.from("a2");
        Coordinate destination = Coordinate.from("a4");

        Assertions.assertThatThrownBy(() -> notRunningTest.move(start, destination))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("움직일 수 없는 게임 상태입니다.");
    }

    @DisplayName("끝낼 수 없는 상태에서 끝내려 할 경우 에러를 발생시킨다.")
    @Test
    void endWhenCanNotEndStatus() {
        NotRunningTestImpl notRunningTest = new NotRunningTestImpl();

        Assertions.assertThatThrownBy(notRunningTest::end)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 끝났거나, 시작되지 않은 상태입니다.");
    }

    @DisplayName("실행중이지 않은 상태에서는 플레이하는 상태가 아니므로 false를 return 한다.")
    @Test
    void isPlayingIfNotRunning() {
        NotRunningTestImpl notRunningTest = new NotRunningTestImpl();

        Assertions.assertThat(notRunningTest.isPlaying()).isFalse();
    }
}
