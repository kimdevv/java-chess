package domain.room;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RoomTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "123456789012345678901"})
    void 룸_이름이_범위를_벗어나면_예외가_발생한다(String invalidName) {
        assertThatThrownBy(() -> new Room(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("룸 이름은 2자 이상 20자 이하여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "12345678901234567890"})
    void 룸_이름이_범위를_벗어나지_않으면_예외가_발생하지_않는다(String validName) {
        assertThatCode(() -> new Room(validName))
                .doesNotThrowAnyException();
    }
}
