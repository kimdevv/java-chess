package view.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("START 또는 LOAD만을 불러와야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"save", "end", "move", "status"})
    void getInitialCommand(String identifier) {
        assertThatThrownBy(() -> Command.getInitialCommand(identifier))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 먼저 시작하거나 불러와야 합니다.");
    }

    @DisplayName("START와 LOAD를 제외하고 불러와야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"start", "load"})
    void getPlayingCommand(String identifier) {
        assertThatThrownBy(() -> Command.getPlayingCommand(identifier))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 시작되었습니다.");
    }

    @DisplayName("올바르지 않는 명령어에 대해선 예외를 발생시킨다.")
    @Test
    void commandException() {
        String identifier = "hi";

        assertThatThrownBy(() -> Command.getInitialCommand(identifier))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당되는 식별자가 존재하지 않습니다.");
        assertThatThrownBy(() -> Command.getPlayingCommand(identifier))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당되는 식별자가 존재하지 않습니다.");
    }
}
