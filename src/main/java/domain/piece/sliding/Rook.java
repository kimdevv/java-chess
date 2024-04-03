package domain.piece.sliding;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Rook extends SlidingPiece {
    private static final int SCORE = 5;

    public Rook(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.EAST,
                Direction.WEST);
    }

    @Override
    public double getScore(final Map<Square, Piece> pieces, final Square square) {
        return SCORE;
    }
}
