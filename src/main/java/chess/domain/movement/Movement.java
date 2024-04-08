package chess.domain.movement;

import chess.domain.movement.direction.Direction;
import chess.domain.movement.policy.Policy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Movement {

    private final Policy policy;
    private final Direction direction;

    public Movement(final Policy policy, final Direction direction) {
        this.policy = policy;
        this.direction = direction;
    }

    public boolean isSatisfied(final Color color, final Position currentPosition, final Piece tartgetPiece) {
        return policy.isSatisfied(color, currentPosition, tartgetPiece);
    }

    public Direction getDirection() {
        return direction;
    }
}
