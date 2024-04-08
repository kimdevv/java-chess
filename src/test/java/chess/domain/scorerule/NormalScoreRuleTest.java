package chess.domain.scorerule;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreRuleTest {

    @Test
    @DisplayName("기본 규칙은 기본 점수 * 말의 개수이다.")
    void findScore() {
        NormalScoreRule normalScoreRule = new NormalScoreRule(3);
        assertThat(normalScoreRule.findScore(List.of(
                Position.of(1, 2),
                Position.of(1, 3)))).isEqualTo(6);
    }
}
