package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.Test;

class BlackPawnTest {
    private final Pawn blackPawn = new BlackPawn();

    @Test
    void 뒤로_움직이는지_확인한다() {
        Position source = PositionFixture.F6;
        Position target = PositionFixture.F7;

        boolean actual = blackPawn.isMovedBack(source, target);

        assertThat(actual).isTrue();
    }
}
