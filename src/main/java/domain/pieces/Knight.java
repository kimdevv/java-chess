package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Knight extends Piece {

    private static final String INITIAL = "N";

    private static final double score = 2.5;

    public Knight(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return Roles.isMovableLimitedCase(direction, movePoint);
    }

    @Override
    public boolean isNoneTeam() {
        return false;
    }

    @Override
    public double getScore() {
        return score;
    }
}
