package chess.domain.movement.policy;

import static chess.domain.pixture.PieceFixture.BLACK_BISHOP;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnemyExistPolicyTest {

    @Test
    @DisplayName("해당 위치의 적이 있을 경우, 해당 정책을 만족한다.")
    void isSatisfied() {
        EnemyExistPolicy policy = new EnemyExistPolicy();

        assertThat(policy.isSatisfied(Color.BLACK, Position.of(1, 1), BLACK_BISHOP.getPiece())).isFalse();
        assertThat(policy.isSatisfied(Color.BLACK, Position.of(1, 1), Empty.getInstance())).isFalse();
    }

    @Test
    @DisplayName("해당 위치의 적이 없는 경우, 해당 정책을 만족하지 않는다.")
    void isNotSatisfied() {
        EnemyExistPolicy policy = new EnemyExistPolicy();

        assertThat(policy.isSatisfied(Color.BLACK, Position.of(1, 1), WHITE_PAWN.getPiece())).isTrue();
    }
}
