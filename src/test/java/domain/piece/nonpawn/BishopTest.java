package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class BishopTest {
    private final Piece bishop = new Bishop(Color.WHITE);
    private final Color targetColor = Color.NEUTRALITY;

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position source = PositionFixture.B1;
        Position target = PositionFixture.C2;

        assertThatCode(() -> bishop.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 직선_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.C1;
        Position target = PositionFixture.C2;

        assertThatThrownBy(() -> bishop.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 방향으로 이동해야 합니다.");
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.F1;
        Position target = PositionFixture.H2;

        assertThatThrownBy(() -> bishop.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 방향으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.B6;
        Position target = PositionFixture.G7;

        assertThatThrownBy(() -> bishop.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 방향으로 이동해야 합니다.");
    }

    @Test
    void 거리에_상관없이_이동할_수_있다() {
        Position source = PositionFixture.A1;
        Position target = PositionFixture.H8;

        assertThatCode(() -> bishop.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }
}
