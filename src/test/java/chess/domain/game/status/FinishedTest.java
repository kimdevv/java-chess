package chess.domain.game.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    @Test
    @DisplayName("Piece가 이동을 했다면 블랙 팀의 차례를 나타내는 상태를 반환한다.")
    void canNotMove() {
        assertThatThrownBy(() -> new Finished().move(Position.of(1, 2), Position.of(1, 4)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 이동이 불가능합니다.");
    }

    @Test
    @DisplayName("현재 차례 조회 시 예외가 발생한다.")
    void getTurn() {
        assertThatThrownBy(() -> new Finished().getTurn())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 턴을 조회할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 보드 조회 시 예외가 발생한다.")
    void getBoard() {
        assertThatThrownBy(() -> new Finished().getBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 보드를 조회할 수 없습니다.");
    }

    @Test
    @DisplayName("게임 종료를 물어볼 경우 항상 참을 반환한다.")
    void isFinished() {
        assertThat(new Finished().isFinish()).isTrue();
    }
}
