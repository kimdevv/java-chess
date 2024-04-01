package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @Test
    @DisplayName("파일을 오른쪽으로 이동시킨다")
    void Given_FileA_When_MoveRight_Then_FileB() {
        //given, when, then
        assertThat(File.A.move(1)).isEqualTo(File.B);
    }

    @Test
    @DisplayName("파일을 왼쪽으로 이동시킨다")
    void Given_FileB_When_MoveLeft_Then_FileA() {
        //given, when, then
        assertThat(File.B.move(-1)).isEqualTo(File.A);
    }

    @ParameterizedTest
    @ValueSource(ints = {-8, 8})
    @DisplayName("범위 밖의 이동 값이 나올경우 예외를 발생시킨다.")
    void Given_FileA_When_MoveExceedStep_Then_Exception(int exceedStep) {
        //give, when, then
        assertThatThrownBy(() -> File.A.move(exceedStep))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 파일 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"3,true", "8,false", "-8,false"})
    @DisplayName("이동 하기 전 범위 밖을 넘어서는지 확인할 수 있다.")
    void Given_FileA_canMoveNext_Then_ReturnBoolean(int step, boolean expected) {
        //given, when, then
        assertThat(File.A.canMoveNext(step)).isEqualTo(expected);
    }

    @Test
    @DisplayName("파일의 최소 값은 A이다.")
    void Given_FileA_When_isMinimum_Then_True() {
        //given, when, then
        assertThat(File.A.isMinimum()).isTrue();
    }

    @Test
    @DisplayName("파일의 최대 값은 h이다.")
    void Given_FileH_When_isMaximum_Then_True() {
        //given, when, then
        assertThat(File.H.isMaximum()).isTrue();
    }
}
