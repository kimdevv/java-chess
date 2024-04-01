package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void Given_Position_When_CreateWithValidFileAndRank_Then_DoesNotThorAnyException() {
        //given, when, then
        assertThatCode(() -> Position.of(File.A, Rank.ONE))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("위치의 file이 최소인 경우 참을 반환한다.")
    void Given_Position_When_IsMinimumFileWithMinimumFile_Then_True() {
        //given
        Position position = Position.of(File.A, Rank.THREE);
        //when, then
        assertThat(position.isMinimumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 file이 최대인 경우 참을 반환한다.")
    void Given_Position_When_IsMaximumFileWithMaximumFile_Then_True() {
        //given
        Position position = Position.of(File.H, Rank.THREE);
        //when, then
        assertThat(position.isMaximumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최소인 경우 참을 반환한다.")
    void Given_Position_When_IsMinimumRankWithMinimumRank_Then_True() {
        //given
        Position position = Position.of(File.C, Rank.ONE);
        //when, then
        assertThat(position.isMinimumRank()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최대인 경우 참을 반환한다.")
    void Given_Position_When_IsMaximumRankWithMaximumRank_Then_True() {
        //given
        Position position = Position.of(File.C, Rank.EIGHT);
        //when, then
        assertThat(position.isMaximumRank()).isTrue();
    }

    @Test
    @DisplayName("최대 최소가 아닌 값이 나올 경우 거짓을 반환한다.")
    void Given_Position_When_IsMinimumAndMaximumPositionWithNotMinMaxPosition_Then_False() {
        //given
        Position position = Position.of(File.C, Rank.THREE);
        //when, then
        assertAll(
                () -> assertThat(position.isMinimumFile()).isFalse(),
                () -> assertThat(position.isMaximumFile()).isFalse(),
                () -> assertThat(position.isMinimumRank()).isFalse(),
                () -> assertThat(position.isMaximumRank()).isFalse()
        );
    }

    @Test
    @DisplayName("현재 위치에서 더할 값이 위치 범위 내에 있으면 참을 반환한다.")
    void Given_Position_When_IsNextPositionInRangeWithAddedVector_Then_True() {
        //given
        Position position = Position.of(File.C, Rank.THREE);
        //when, then
        assertThat(position.isNextPositionInRange(Vector.DOWN_DOWN_LEFT)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더할 값이 위치 범위 밖에 있으면 거짓을 반환한다.")
    void Given_Position_When_IsNextPositionInRangeWithAddedVector_Then_False() {
        //given
        Position position = Position.of(File.H, Rank.TWO);
        //when, then
        assertAll(
                () -> assertThat(position.isNextPositionInRange(Vector.RIGHT_RIGHT_UP)).isFalse(),
                () -> assertThat(position.isNextPositionInRange(Vector.DOWN_DOWN_RIGHT)).isFalse()
        );
    }
}
