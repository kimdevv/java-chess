package domain.piece.sliding;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Bishop extends SlidingPiece {
    private static final int SCORE = 3;

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST);
    }

    @Override
    public double getScore(final Map<Square, Piece> pieces, final Square square) {
        return SCORE;
    }
}
