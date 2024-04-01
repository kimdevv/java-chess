package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyStateTest {

    @DisplayName("준비 상태에서 시작하면 흰색 상태가 된다")
    @Test
    void givenReadyStateWhenStartThenReturnWhiteState() {
        ReadyState readyState = ReadyState.getInstance();

        assertThat(readyState.start()).isInstanceOf(WhiteState.class);
    }

    @DisplayName("준비 상태일 때 움직이면 에러가 발생한다")
    @Test
    void givenReadyStateWhenMoveThenThrowsException() {
        ReadyState readyState = ReadyState.getInstance();

        assertThatCode(
                () -> readyState.move(BoardFactory.createInitialBoard(), Position.from("a2"), Position.from("a3")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("준비 상태에서는 움직일 수 없습니다.");
    }

    @DisplayName("준비 상태에서 종료하면 종료 상태가 된다")
    @Test
    void givenReadyStateWhenEndThenReturnEndState() {
        ReadyState readyState = ReadyState.getInstance();

        assertThat(readyState.end()).isInstanceOf(EndState.class);
    }

    @DisplayName("준비 상태에서 점수를 계산하면 에러가 발생한다")
    @Test
    void givenReadyStateWhenStatusThenThrowsException() {
        ReadyState readyState = ReadyState.getInstance();

        assertThatCode(readyState::status)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("준비 상태에서는 점수를 계산할 수 없습니다.");
    }

    @DisplayName("준비 상태에서 실행중인지 확인하면 true를 반환한다")
    @Test
    void givenReadyStateWhenIsPlayingThenReturnTrue() {
        ReadyState readyState = ReadyState.getInstance();

        assertThat(readyState.isPlaying()).isTrue();
    }
}
