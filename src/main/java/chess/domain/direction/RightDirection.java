package chess.domain.direction;

import chess.domain.position.Position;

public class RightDirection extends SlidingDirection {

    public RightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumFile()) {
            return position;
        }
        return Position.of(position.file().move(1), position.rank());
    }
}
