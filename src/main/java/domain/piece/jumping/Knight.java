package domain.piece.jumping;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Knight extends JumpingPiece {
    private static final double SCORE = 2.5;

    public Knight(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of(
                Direction.NORTH_NORTH_EAST,
                Direction.NORTH_NORTH_WEST,
                Direction.SOUTH_SOUTH_EAST,
                Direction.SOUTH_SOUTH_WEST,
                Direction.EAST_EAST_NORTH,
                Direction.EAST_EAST_SOUTH,
                Direction.WEST_WEST_NORTH,
                Direction.WEST_WEST_SOUTH
        );
    }

    @Override
    public double getScore(final Map<Square, Piece> pieces, final Square square) {
        return SCORE;
    }
}
