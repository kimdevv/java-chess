package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Bishop extends Piece {

    private static final String INITIAL = "B";

    private static final double score = 3;

    public Bishop(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return Roles.isMovableUnlimitedCase(direction, pieces, movePoint);
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
