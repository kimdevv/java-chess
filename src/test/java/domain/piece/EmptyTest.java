package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class EmptyTest {
    private final Piece empty = Empty.create();
    private final Color targetColor = Color.BLACK;

    @Test
    void Empty_기물의_움직임을_검증하면_예외가_발생한다() {
        Position source = PositionFixture.A8;
        Position target = PositionFixture.B3;

        assertThatThrownBy(() -> empty.validateMovement(source, target, targetColor))
                .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    void Empty_기물의_타입은_빈_타입이다() {
        assertThat(empty.type()).isEqualTo(Type.EMPTY);
    }

    @Test
    void Empty_기물의_색은_중립이다() {
        assertThat(empty.color()).isEqualTo(Color.NEUTRALITY);
    }
}
