package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수의 합계를 계산한다.")
    void Score_Sum_with_other_score() {
        var sut = new Score(20);

        var result = sut.sum(new Score(12));

        assertThat(result).isEqualTo(new Score(32));
    }
}
