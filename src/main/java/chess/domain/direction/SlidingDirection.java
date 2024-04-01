package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import java.util.stream.Stream;

abstract class SlidingDirection implements Direction {

    private final int moveCount;

    protected SlidingDirection(final int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return Stream.iterate(next(source), now -> isPassable(target, obstacle, now), this::next)
                .limit(moveCount)
                .anyMatch(position -> isReached(target, position));
    }

    private boolean isPassable(final Position target, final Obstacle obstacle, final Position now) {
        return !obstacle.isBlocked(now, target);
    }

    private boolean isReached(final Position target, final Position position) {
        return position.equals(target);
    }

    abstract Position next(final Position position);
}
