package chess.domain.direction;

import chess.domain.position.Position;

public class UpDirection extends SlidingDirection {

    public UpDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumRank()) {
            return position;
        }
        return Position.of(position.file(), position.rank().move(1));
    }
}
