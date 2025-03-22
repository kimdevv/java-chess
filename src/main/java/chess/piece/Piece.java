package chess.piece;

import chess.position.Position;

import java.util.List;

public abstract class Piece {

    protected Position position;

    public Piece(final Position position) {
        this.position = position;
    }

    public abstract List<Position> calculateRouteToDestination(final Position destination);
}
