package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class PawnTest {
    private final Piece pawn = new WhitePawn();

    @Test
    void 수직_방향으로_한_칸_이동할_수_있다() {
        Position source = PositionFixture.A3;
        Position target = PositionFixture.A4;
        Color targetColor = Color.NEUTRALITY;

        assertThatCode(() -> pawn.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 수평_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.A1;
        Position target = PositionFixture.B1;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 앞으로 이동해야 합니다.");
    }

    @Test
    void 앞이_빈_공간이_아니면_직선_방향으로_이동할_수_없다() {
        Position source = PositionFixture.A5;
        Position target = PositionFixture.A6;
        Color targetColor = Color.BLACK;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동하려는 칸에 기물이 존재합니다.");
    }

    @Test
    void 초기_칸일_때_직선_방향으로_두_칸_이동할_수_있다() {
        Position source = PositionFixture.B2;
        Position target = PositionFixture.B4;
        Color targetColor = Color.NEUTRALITY;

        assertThatCode(() -> pawn.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 초기_칸일_때_직선_방향으로_세_칸_이상_이동하면_예외가_발생한다() {
        Position source = PositionFixture.C2;
        Position target = PositionFixture.C5;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초기 칸에서 최대 2칸 이동할 수 있습니다.");
    }

    @Test
    void 초기_칸이_아닐_때_직선_방향으로_한_칸_이동할_수_있다() {
        Position source = PositionFixture.B5;
        Position target = PositionFixture.B6;
        Color targetColor = Color.NEUTRALITY;

        assertThatCode(() -> pawn.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 초기_칸이_아닐_때_직선_방향으로_두_칸_이상_이동하면_예외가_발생한다() {
        Position source = PositionFixture.D3;
        Position target = PositionFixture.D5;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 1칸 이동할 수 있습니다.");
    }

    @Test
    void 대각선_방향에_적이_있으면_이동할_수_있다() {
        Position source = PositionFixture.H3;
        Position target = PositionFixture.G4;
        Color targetColor = Color.BLACK;

        assertThatCode(() -> pawn.validateMovement(source, target, targetColor))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향에_적이_없을_때_이동할_경우_예외가_발생한다() {
        Position source = PositionFixture.H4;
        Position target = PositionFixture.G5;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 방향으로 이동은 상대 기물이 있을 때만 가능합니다.");
    }

    @Test
    void 대각선_방향으로_두_칸_이동할_경우_예외가_발생한다() {
        Position source = PositionFixture.F4;
        Position target = PositionFixture.D6;
        Color targetColor = Color.BLACK;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 1칸 이동할 수 있습니다.");
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.G3;
        Position target = PositionFixture.F5;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 앞으로 이동해야 합니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.H2;
        Position target = PositionFixture.A7;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대각선 또는 앞으로 이동해야 합니다.");
    }

    @Test
    void 뒤로_이동하면_예외가_발생한다() {
        Position source = PositionFixture.D2;
        Position target = PositionFixture.D1;
        Color targetColor = Color.NEUTRALITY;

        assertThatThrownBy(() -> pawn.validateMovement(source, target, targetColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("뒤로 이동할 수 없습니다.");
    }
}
