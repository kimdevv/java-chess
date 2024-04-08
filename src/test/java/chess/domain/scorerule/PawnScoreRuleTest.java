package chess.domain.scorerule;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnScoreRuleTest {

    @Test
    @DisplayName("같은 세로 줄에 같은 색의 폰이 여러개 있는 경우, 각각 0.5점")
    void findScoreWithSameFilePositions() {
        PawnScoreRule pawnScoreRule = new PawnScoreRule();
        assertThat(pawnScoreRule.findScore(List.of(
                Position.of(1, 2),
                Position.of(1, 3)
        ))).isEqualTo(1);
    }

    @Test
    @DisplayName("같은 세로 줄에 같은 색의 폰이 하나만 있는 경우, 1점(기본점수)")
    void findScore() {
        PawnScoreRule pawnScoreRule = new PawnScoreRule();
        assertThat(pawnScoreRule.findScore(List.of(
                Position.of(1, 2),
                Position.of(2, 3)
        ))).isEqualTo(2);
    }
}
