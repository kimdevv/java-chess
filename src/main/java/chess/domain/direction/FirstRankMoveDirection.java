package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class FirstRankMoveDirection implements Direction {

    private final Direction direction;
    private final Rank startRank;

    public FirstRankMoveDirection(final Direction direction, final Rank startRank) {
        this.direction = direction;
        this.startRank = startRank;
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        if (isStartRank(source)) {
            return direction.canReach(source, target, obstacle);
        }
        return false;
    }

    private boolean isStartRank(final Position source) {
        return source.rank().equals(startRank);
    }
}
