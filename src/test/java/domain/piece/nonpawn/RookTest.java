package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class RookTest {
    private final Piece rook = new Rook(Color.WHITE);
    private final Color targetColor = Color.NEUTRALITY;

    @Test
    void 수직_방향으로_이동할_수_있다() {
        Position source = PositionFixture.C1;
        Position target = PositionFixture.C4;

        assertThatCode(() -> rook.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 수평_방향으로_이동할_수_있다() {
        Position source = PositionFixture.E6;
        Position target = PositionFixture.G6;

        assertThatCode(() -> rook.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.G8;
        Position target = PositionFixture.H7;

        assertThatThrownBy(() -> rook.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("직선 방향으로 이동해야 합니다.");
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.H5;
        Position target = PositionFixture.F6;

        assertThatThrownBy(() -> rook.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.G1;
        Position target = PositionFixture.D7;

        assertThatThrownBy(() -> rook.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 거리에_상관없이_이동할_수_있다() {
        Position source = PositionFixture.H1;
        Position target = PositionFixture.H8;

        assertThatCode(() -> rook.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }
}
