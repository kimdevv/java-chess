package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import java.util.List;

public class CombinationDirection implements Direction {

    private final List<Direction> directions;

    public CombinationDirection(final Direction... directions) {
        this.directions = List.of(directions);
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return directions.stream()
                .anyMatch(direction -> checkOneDirectionCanReach(source, target, obstacle, direction));
    }

    private boolean checkOneDirectionCanReach(final Position source, final Position target, final Obstacle obstacle,
                                              final Direction direction) {
        return direction.canReach(source, target, obstacle);
    }
}
