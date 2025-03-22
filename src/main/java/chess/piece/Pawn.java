package chess.piece;

import chess.position.Position;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Position position) {
        super(position);
    }

    @Override
    public List<Position> calculateRouteToDestination(final Position destination) {
        return null;
    }
}
