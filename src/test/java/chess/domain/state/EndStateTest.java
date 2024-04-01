package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndStateTest {

    @DisplayName("종료 상태에서 시작하면 에러가 발생한다")
    @Test
    void givenEndStateWhenStartThenThrowsException() {
        EndState endState = EndState.getInstance();

        assertThatCode(endState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료 상태에서는 시작할 수 없습니다.");
    }

    @DisplayName("종료 상태에서 움직이면 에러가 발생한다")
    @Test
    void givenEndStateWhenMoveThenThrowsException() {
        EndState endState = EndState.getInstance();

        assertThatCode(
                () -> endState.move(BoardFactory.createInitialBoard(), Position.from("a1"), Position.from("a2")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료 상태에서는 움직일 수 없습니다.");
    }

    @DisplayName("종료 상태에서 종료하면 에러가 발생한다")
    @Test
    void givenEndStateWhenEndThenThrowsException() {
        EndState endState = EndState.getInstance();

        assertThatCode(endState::end)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료 상태에서는 종료할 수 없습니다.");
    }

    @DisplayName("종료 상태에서 점수를 계산하면 에러가 발생한다")
    @Test
    void givenEndStateWhenStatusThenThrowsException() {
        EndState endState = EndState.getInstance();

        assertThatCode(endState::status)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료 상태에서는 점수를 계산할 수 없습니다.");
    }

    @DisplayName("종료 상태에서 플레이중인지 확인하면 false를 반환한다")
    @Test
    void givenEndStateWhenIsPlayingThenReturnFalse() {
        EndState endState = EndState.getInstance();

        assertThat(endState.isPlaying()).isFalse();
    }
}
