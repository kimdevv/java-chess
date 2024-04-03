package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    void UP_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.F, Rank.FIVE);
        Position target = Position.of(File.F, Rank.EIGHT);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.F, Rank.SIX),
                Position.of(File.F, Rank.SEVEN)
        );
    }

    @Test
    void UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.H, Rank.SEVEN);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.F, Rank.FIVE),
                Position.of(File.G, Rank.SIX));
    }

    @Test
    void RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FIVE);
        Position target = Position.of(File.H, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.F, Rank.FIVE),
                Position.of(File.G, Rank.FIVE)
        );
    }

    @Test
    void DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.SIX);
        Position target = Position.of(File.H, Rank.THREE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.F, Rank.FIVE),
                Position.of(File.G, Rank.FOUR));
    }

    @Test
    void DOWN_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.F, Rank.FIVE);
        Position target = Position.of(File.F, Rank.TWO);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.F, Rank.FOUR),
                Position.of(File.F, Rank.THREE)
        );
    }

    @Test
    void DOWN_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.F, Rank.FIVE);
        Position target = Position.of(File.C, Rank.TWO);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.E, Rank.FOUR),
                Position.of(File.D, Rank.THREE)
        );
    }

    @Test
    void LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.F, Rank.FIVE);
        Position target = Position.of(File.C, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.E, Rank.FIVE),
                Position.of(File.D, Rank.FIVE)
        );
    }

    @Test
    void UP_LEFT_방향으로_이동하는_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.A, Rank.EIGHT);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).containsExactly(
                Position.of(File.D, Rank.FIVE),
                Position.of(File.C, Rank.SIX),
                Position.of(File.B, Rank.SEVEN)
        );
    }

    @Test
    void UP_UP_LEFT_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.D, Rank.SIX);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void UP_UP_RIGHT_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.F, Rank.SIX);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void RIGHT_RIGHT_UP_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.G, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void RIGHT_RIGHT_DOWN_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.G, Rank.THREE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void DOWN_DOWN_RIGHT_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.F, Rank.TWO);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void DOWN_DOWN_LEFT_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.D, Rank.TWO);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void LEFT_LEFT_DOWN_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.C, Rank.THREE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }

    @Test
    void LEFT_LEFT_UP_방향으로_이동할_경우_빈_경로를_반환한다() {
        Position source = Position.of(File.E, Rank.FOUR);
        Position target = Position.of(File.C, Rank.FIVE);

        List<Position> positions = source.findPathTo(target);

        assertThat(positions).hasSize(0);
    }
}
