package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class QueenTest {
    private final Piece queen = new Queen(Color.WHITE);
    private final Color targetColor = Color.NEUTRALITY;

    @Test
    void 수직_방향으로_이동할_수_있다() {
        Position source = PositionFixture.D6;
        Position target = PositionFixture.D5;

        assertThatCode(() -> queen.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 수평_방향으로_이동할_수_있다() {
        Position source = PositionFixture.G2;
        Position target = PositionFixture.F2;

        assertThatCode(() -> queen.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position source = PositionFixture.F1;
        Position target = PositionFixture.E2;

        assertThatCode(() -> queen.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();

    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.C6;
        Position target = PositionFixture.E7;

        assertThatThrownBy(() -> queen.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.F1;
        Position target = PositionFixture.A3;

        assertThatThrownBy(() -> queen.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 거리에_상관없이_이동할_수_있다() {
        Position source = PositionFixture.D1;
        Position target = PositionFixture.D8;

        assertThatCode(() -> queen.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }
}
