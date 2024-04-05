package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardStatusConverterTest {

    @DisplayName("보드 상태를 문자열로 변환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_TURN, BLACK_TURN", "WHITE_TURN, WHITE_TURN"})
    void convertToString(BoardStatus boardStatus, String expected) {
        String actual = BoardStatusConverter.convertToString(boardStatus);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("문자열을 보드 상태로 변환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_TURN, BLACK_TURN", "WHITE_TURN, WHITE_TURN"})
    void convertToBoardStatus(String string, BoardStatus boardStatus) {
        BoardStatus actual = BoardStatusConverter.convertToBoardStatus(string);

        assertThat(actual).isEqualTo(boardStatus);
    }
}
