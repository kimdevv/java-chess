package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class DiagonalDirection implements Direction {

    private final CombinationDirection combinationDirection;

    public DiagonalDirection(final int moveCount) {
        this.combinationDirection = new CombinationDirection(
                new UpLeftDirection(moveCount), new UpRightDirection(moveCount),
                new DownRightDirection(moveCount), new DownLeftDirection(moveCount));
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return combinationDirection.canReach(source, target, obstacle);
    }
}
