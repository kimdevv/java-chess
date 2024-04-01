package chess.domain.movementpolicy;

import chess.domain.direction.Direction;
import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class SameMovementPolicy implements MovementPolicy {

    private final Direction direction;

    public SameMovementPolicy(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean canAttack(final Position source, final Position target, final Obstacle obstacle) {
        return direction.canReach(source, target, obstacle);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Obstacle obstacle) {
        return direction.canReach(source, target, obstacle);
    }
}
