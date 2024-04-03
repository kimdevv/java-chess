package chess.domain.piece;

import chess.domain.point.Point;

public final class Rook extends MultiMovePiece {

    public Rook(final Team team) {
        super(Type.ROOK, team);
    }

    @Override
    protected boolean isMovablePoint(final Point departure, final Point destination) {
        return isNotSamePoint(departure, destination) &&
                departure.isStraight(destination);
    }
}
