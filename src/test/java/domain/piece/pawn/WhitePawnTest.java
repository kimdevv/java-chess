package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class WhitePawnTest {
    private final Pawn whitePawn = new WhitePawn();

    @Test
    void 뒤로_움직이는지_확인한다() {
        Position source = PositionFixture.D2;
        Position target = PositionFixture.D1;

        boolean actual = whitePawn.isMovedBack(source, target);

        assertThat(actual).isTrue();
    }
}
