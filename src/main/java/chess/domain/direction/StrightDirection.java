package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class StrightDirection implements Direction {

    private final CombinationDirection combinationDirection;

    public StrightDirection(final int moveCount) {
        this.combinationDirection = new CombinationDirection(
                new UpDirection(moveCount), new DownDirection(moveCount),
                new LeftDirection(moveCount), new RightDirection(moveCount));
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return combinationDirection.canReach(source, target, obstacle);
    }
}
