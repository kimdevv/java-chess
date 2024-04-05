package domain.room;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRoomTest {

    @DisplayName("방의 이름이 최대를 넘을 경우 에러를 발생한다.")
    @Test
    void overMaxLengthRoomName() {
        Assertions.assertThatThrownBy(() -> new GameRoom("자이제한번20글자를넘어보도록하겠습니다."))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("설정 가능한 방 이름이 아닙니다.");
    }
}
