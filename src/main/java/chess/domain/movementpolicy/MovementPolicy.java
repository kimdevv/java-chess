package chess.domain.movementpolicy;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public interface MovementPolicy {
    boolean canAttack(final Position source, final Position target, final Obstacle obstacles);

    boolean canMove(final Position source, final Position target, final Obstacle obstacles);
}
