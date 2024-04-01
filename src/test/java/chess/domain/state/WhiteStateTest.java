package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteStateTest {

    @DisplayName("백의 상태일 때 시작하면 에러가 발생한다")
    @Test
    void givenWhiteStateWhenStartThenThrowsException() {
        BlackState blackState = BlackState.getInstance();

        assertThatCode(blackState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("이미 시작한 상태입니다.");
    }

    @DisplayName("백의 상태일 때 움직이면 흑의 상태가 된다")
    @Test
    void givenWhiteStateWhenMoveThenReturnBlackState() {
        WhiteState WhiteState = chess.domain.state.WhiteState.getInstance();

        GameState state = WhiteState.move(BoardFactory.createInitialBoard(), Position.from("a2"), Position.from("a3"));

        assertThat(state).isInstanceOf(BlackState.class);
    }

    @DisplayName("백의 상태일 때 종료하면 종료 상태가 된다")
    @Test
    void givenWhiteStateWhenEndThenReturnEndState() {
        WhiteState WhiteState = chess.domain.state.WhiteState.getInstance();

        assertThat(WhiteState.end()).isInstanceOf(EndState.class);
    }

    @DisplayName("백의 상태일 때 점수를 계산하면 백의 상태가 유지된다")
    @Test
    void givenWhiteStateWhenStatusThenReturnWhiteState() {
        WhiteState WhiteState = chess.domain.state.WhiteState.getInstance();

        assertThat(WhiteState.status()).isInstanceOf(WhiteState.class);
    }

    @DisplayName("백의 상태일 때 플레이중인지 확인하면 true를 반환한다")
    @Test
    void givenWhiteStateWhenIsPlayingThenReturnTrue() {
        WhiteState whiteState = chess.domain.state.WhiteState.getInstance();

        assertThat(whiteState.isPlaying()).isTrue();
    }
}
