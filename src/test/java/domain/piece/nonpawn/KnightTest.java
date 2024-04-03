package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class KnightTest {
    private final Piece knight = new Knight(Color.WHITE);
    private final Color targetColor = Color.NEUTRALITY;

    @Test
    void L자_방향으로_이동할_수_있다() {
        Position source = PositionFixture.D4;
        Position target = PositionFixture.F3;

        assertThatCode(() -> knight.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.C3;
        Position target = PositionFixture.G7;

        assertThatThrownBy(() -> knight.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L자 방향으로 이동해야 합니다.");
    }

    @Test
    void 직선_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.E4;
        Position target = PositionFixture.E8;

        assertThatThrownBy(() -> knight.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L자 방향으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.E8;
        Position target = PositionFixture.A2;

        assertThatThrownBy(() -> knight.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("L자 방향으로 이동해야 합니다.");
    }
}
