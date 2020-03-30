package domain.pieces;

import domain.move.Direction;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Empty extends Piece {

    private static final String INITIAL = ".";

    private static final double score = 0;

    public Empty(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return false;
    }

    @Override
    public boolean isNoneTeam() {
        return true;
    }

    @Override
    public double getScore() {
        return score;
    }
}
