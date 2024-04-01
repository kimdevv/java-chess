package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackStateTest {

    @DisplayName("흑의 상태일 때 시작하면 에러가 발생한다")
    @Test
    void givenBlackStateWhenStartThenThrowsException() {
        BlackState blackState = BlackState.getInstance();

        assertThatCode(blackState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("이미 시작한 상태입니다.");
    }

    @DisplayName("흑의 상태일 때 움직이면 백의 상태가 된다")
    @Test
    void givenBlackStateWhenMoveThenReturnWhiteState() {
        BlackState blackState = BlackState.getInstance();

        GameState state = blackState.move(BoardFactory.createInitialBoard(), Position.from("a7"), Position.from("a6"));

        assertThat(state).isInstanceOf(WhiteState.class);
    }

    @DisplayName("흑의 상태일 때 종료하면 종료 상태가 된다")
    @Test
    void givenBlackStateWhenEndThenReturnEndState() {
        BlackState blackState = BlackState.getInstance();

        assertThat(blackState.end()).isInstanceOf(EndState.class);
    }

    @DisplayName("흑의 상태일 때 점수를 계산하면 흑의 상태가 유지된다")
    @Test
    void givenBlackStateWhenStatusThenReturnBlackState() {
        BlackState blackState = BlackState.getInstance();

        assertThat(blackState.status()).isInstanceOf(BlackState.class);
    }

    @DisplayName("흑의 상태일 때 플레이중인지 확인하면 true를 반환한다")
    @Test
    void givenBlackStateWhenIsPlayingThenReturnTrue() {
        BlackState blackState = BlackState.getInstance();

        assertThat(blackState.isPlaying()).isTrue();
    }
}
