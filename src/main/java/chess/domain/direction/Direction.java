package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public interface Direction {

    boolean canReach(final Position source, final Position target, final Obstacle obstacle);
}
