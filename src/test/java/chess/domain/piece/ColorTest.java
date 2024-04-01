package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {
    @Test
    @DisplayName("현재 가지고 있는 보색을 반환할 수 있다.")
    void Given_ColorWithWhite_When_Opposite_Then_ReturnColorBlack() {
        assertAll(
                () -> assertThat(Color.WHITE.opposite()).isEqualTo(Color.BLACK),
                () -> assertThat(Color.BLACK.opposite()).isEqualTo(Color.WHITE)
        );
    }

    @Test
    @DisplayName("색이 없을 경우 예외를 처리한다.")
    void Given_ColorWithNone_When_Opposite_Then_Exception() {
        assertThatThrownBy(Color.NONE::opposite)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색깔이 존재하지 않아 보색을 반환할 수 없습니다.");
    }
}
