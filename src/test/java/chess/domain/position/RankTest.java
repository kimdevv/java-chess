package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Test
    @DisplayName("랭크를 위로 이동시킨다")
    void Given_RankOne_When_MoveUp_Then_RankTwo() {
        //given, when, then
        assertThat(Rank.ONE.move(1)).isEqualTo(Rank.TWO);
    }

    @Test
    @DisplayName("랭크를 아래로 이동시킨다")
    void Given_RankThree_When_MoveLeft_Then_RankTwo() {
        //given, when, then
        assertThat(Rank.THREE.move(-1)).isEqualTo(Rank.TWO);
    }

    @ParameterizedTest
    @ValueSource(ints = {-8, 8})
    @DisplayName("범위 밖의 이동 값이 나올경우 예외를 발생시킨다.")
    void Given_RankOne_When_MoveExceedStep_Then_Exception(int exceedStep) {
        //give, when, then
        assertThatThrownBy(() -> Rank.ONE.move(exceedStep))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 랭크 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"3,true", "8,false", "-8,false"})
    @DisplayName("이동 하기 전 범위 밖을 넘어서는지 확인할 수 있다.")
    void Given_RankOne_canMoveNext_Then_ReturnBoolean(int step, boolean expected) {
        //given, when, then
        assertThat(Rank.ONE.canMoveNext(step)).isEqualTo(expected);
    }

    @Test
    @DisplayName("랭크의 최소 값은 1이다.")
    void Given_RankOne_When_isMinimum_Then_True() {
        //given, when, then
        assertThat(Rank.ONE.isMinimum()).isTrue();
    }

    @Test
    @DisplayName("랭크의 최대 값은 8이다.")
    void Given_RankEight_When_isMaximum_Then_True() {
        //given, when, then
        assertThat(Rank.EIGHT.isMaximum()).isTrue();
    }
}
