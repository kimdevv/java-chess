package chess.domain.piece;

import chess.domain.point.Point;

public final class King extends SingleMovePiece {

    public King(final Team team) {
        super(Type.KING, team);
    }

    @Override
    protected boolean isMovablePoint(final Point departure, final Point destination) {
        return isNotSamePoint(departure, destination) &&
                departure.isAround(destination);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
