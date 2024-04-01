package chess.domain.movementpolicy;

import chess.domain.direction.Direction;
import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class DifferentPolicy implements MovementPolicy {

    private final Direction attackDirection;
    private final Direction moveDirection;

    public DifferentPolicy(final Direction attackDirection, final Direction moveDirection) {
        this.attackDirection = attackDirection;
        this.moveDirection = moveDirection;
    }

    @Override
    public boolean canAttack(final Position source, final Position target, final Obstacle obstacle) {
        return attackDirection.canReach(source, target, obstacle);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Obstacle obstacle) {
        return moveDirection.canReach(source, target, obstacle);
    }
}
