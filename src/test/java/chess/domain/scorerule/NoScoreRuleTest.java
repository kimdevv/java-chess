package chess.domain.scorerule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoScoreRuleTest {

    @Test
    @DisplayName("점수가 없는 경우는 0을 반환한다.")
    void findScore() {
        NoScoreRule noScoreRule = new NoScoreRule();
        assertAll(
                () -> assertThat(noScoreRule.findScore(List.of())).isEqualTo(0),
                () -> assertThat(noScoreRule.findScore(List.of(Position.of(1, 3)))).isEqualTo(0)
        );
    }
}
