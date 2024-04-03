package chess.domain.piece;

import chess.domain.point.Point;

import java.util.Map;

public abstract class SingleMovePiece extends Piece {

    public SingleMovePiece(final Type type, final Team team) {
        super(type, team);
    }

    @Override
    public boolean canMove(final Point departure, final Point destination, final Map<Point, Piece> board) {
        if (hasSameTeamPieceAtDestination(board.get(departure), board.get(destination))) {
            return false;
        }
        return isMovablePoint(departure, destination);
    }
}
