package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class DiagonalStraightDirection implements Direction {

    private final CombinationDirection combinationDirection;

    public DiagonalStraightDirection(final int moveCount) {
        this.combinationDirection = new CombinationDirection(
                new DiagonalDirection(moveCount), new StrightDirection(moveCount));
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return combinationDirection.canReach(source, target, obstacle);
    }
}
