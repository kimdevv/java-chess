package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.ClearBoardInitializer;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameTest {

    @ParameterizedTest
    @CsvSource({"BLACK", "WHITE"})
    @DisplayName("게임 생성 시 해당하는 색에 맞는 차례인 상태로 생성한다.")
    void of(final Color color) {
        Game game = Game.of(new ClearBoardInitializer(), color);
        assertThat(game.getTurn()).isEqualTo(color);
    }
}
