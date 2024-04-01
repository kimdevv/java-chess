package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {

    @DisplayName("기물의 타입이 폰인지 반환한다")
    @Test
    void isPawn() {
        assertThat(PieceType.PAWN.isPawn()).isTrue();
        assertThat(PieceType.KNIGHT.isPawn()).isFalse();
    }
}
