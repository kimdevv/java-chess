package chess.domain.piece;

import chess.domain.point.Point;

public final class Queen extends MultiMovePiece {

    public Queen(final Team team) {
        super(Type.QUEEN, team);
    }

    @Override
    protected boolean isMovablePoint(final Point departure, final Point destination) {
        return isNotSamePoint(departure, destination) &&
                (departure.isStraight(destination) || departure.isDiagonalWithSlopeOfOne(destination));
    }
}
