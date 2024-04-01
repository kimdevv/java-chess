package chess.domain.piece;

import chess.domain.direction.CombinationDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DiagonalStraightDirection;
import chess.domain.direction.DownDirection;
import chess.domain.direction.DownLeftDirection;
import chess.domain.direction.DownRightDirection;
import chess.domain.direction.FirstRankMoveDirection;
import chess.domain.direction.KnightDirection;
import chess.domain.direction.NoMoveDirection;
import chess.domain.direction.StrightDirection;
import chess.domain.direction.UpDirection;
import chess.domain.direction.UpLeftDirection;
import chess.domain.direction.UpRightDirection;
import chess.domain.movementpolicy.DifferentPolicy;
import chess.domain.movementpolicy.MovementPolicy;
import chess.domain.movementpolicy.SameMovementPolicy;
import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public enum PieceType {
    
    KING(new SameMovementPolicy(new DiagonalStraightDirection(1))),
    QUEEN(new SameMovementPolicy(new DiagonalStraightDirection(8))),
    BISHOP(new SameMovementPolicy(new DiagonalDirection(8))),
    ROOK(new SameMovementPolicy(new StrightDirection(8))),
    KNIGHT(new SameMovementPolicy(new KnightDirection())),
    WHITE_PAWN(new DifferentPolicy(
            new CombinationDirection(new UpLeftDirection(1), new UpRightDirection(1)),
            new CombinationDirection(new FirstRankMoveDirection(new UpDirection(2), Rank.TWO), new UpDirection(1)))),
    BLACK_PAWN(new DifferentPolicy(
            new CombinationDirection(new DownRightDirection(1), new DownLeftDirection(1)),
            new CombinationDirection(new FirstRankMoveDirection(new DownDirection(2), Rank.SEVEN),
                    new UpDirection(1)))),
    EMPTY(new SameMovementPolicy(new NoMoveDirection())),
    ;

    private final MovementPolicy movementPolicy;

    PieceType(MovementPolicy movementPolicy) {
        this.movementPolicy = movementPolicy;
    }

    public boolean canAttack(final Position source, final Position target, final Obstacle obstacle) {
        return movementPolicy.canAttack(source, target, obstacle);
    }

    public boolean canMove(final Position source, final Position target, final Obstacle obstacle) {
        return movementPolicy.canMove(source, target, obstacle);
    }
}
