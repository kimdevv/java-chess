package domain.piece.nonpawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class KingTest {
    private final Piece king = new King(Color.WHITE);
    private final Color targetColor = Color.NEUTRALITY;

    @Test
    void 수직_방향으로_이동할_수_있다() {
        Position source = PositionFixture.B8;
        Position target = PositionFixture.B7;

        assertThatCode(() -> king.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 수평_방향으로_이동할_수_있다() {
        Position source = PositionFixture.C8;
        Position target = PositionFixture.D8;

        assertThatCode(() -> king.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향으로_이동할_수_있다() {
        Position source = PositionFixture.E3;
        Position target = PositionFixture.F2;

        assertThatCode(() -> king.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.F8;
        Position target = PositionFixture.H7;

        assertThatThrownBy(() -> king.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 직선 방향으로 이동해야 합니다.");
    }

    @Test
    void 한_칸만_이동할_수_있다() {
        Position source = PositionFixture.H8;
        Position target = PositionFixture.H7;

        assertThatCode(() -> king.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 한_칸_이상_이동하면_예외가_발생한다() {
        Position source = PositionFixture.E7;
        Position target = PositionFixture.E5;

        assertThatThrownBy(() -> king.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 1칸 이동할 수 있습니다");
    }
}
