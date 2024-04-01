package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class NoMoveDirection implements Direction {

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return false;
    }
}
