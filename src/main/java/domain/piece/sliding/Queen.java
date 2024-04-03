package domain.piece.sliding;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Queen extends SlidingPiece {
    private static final int SCORE = 9;

    public Queen(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.EAST,
                Direction.WEST,
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
