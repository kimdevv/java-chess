package chess.domain.direction;

import chess.domain.position.Position;

public class UpRightDirection extends SlidingDirection {

    public UpRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumFile() || position.isMaximumRank()) {
            return position;
        }
        return Position.of(position.file().move(1), position.rank().move(1));
    }
}
