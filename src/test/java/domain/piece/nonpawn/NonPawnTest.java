package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class NonPawnTest {
    private final Piece nonPawn = new Queen(Color.WHITE);

    @Test
    void 이동할_위치에_같은_색의_기물이_있으면_예외가_발생한다() {
        Position source = PositionFixture.C6;
        Position target = PositionFixture.C7;
        Color targetColor = Color.WHITE;

        assertThatThrownBy(() -> nonPawn.validateMovement(source, target, targetColor))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 색의 기물이 존재합니다");
    }

    @Test
    void 이동할_위치에_다른_색의_기물이_있으면_예외가_발생하지_않는다() {
        Position source = PositionFixture.E1;
        Position target = PositionFixture.E2;
        Color targetColor = Color.BLACK;

        assertThatCode(() -> nonPawn.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }
}
