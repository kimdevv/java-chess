package chess.piece;

import chess.player.Team;
import chess.position.Position;

import java.util.List;

public class Pawn extends Piece {

    private final Team team;

    public Pawn(final Position position, final Team team) {
        super(position);
        this.team = team;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public List<Position> calculateRouteToDestination(final Position destination) {
        final int rowDifference = destination.calculateRowDifference(position);
        final int columnDifference = destination.calculateColumnDifference(position);
        return selectRouteFromDifferences(rowDifference, columnDifference);
    }

    private List<Position> selectRouteFromDifferences(final int rowDifference, final int columnDifference) {
        if (team == Team.BLACK) {
            if (rowDifference == 1 && Math.abs(columnDifference) == 0) {
                return calculateVerticalRoute(rowDifference);
            }
            if (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) {
                return calculateHorizontalRoute(columnDifference);
            }
            throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
        }
        if (rowDifference == -1 && Math.abs(columnDifference) == 0) {
            return calculateVerticalRoute(rowDifference);
        }
        if (Math.abs(rowDifference) == 0 && Math.abs(columnDifference) == 1) {
            return calculateHorizontalRoute(columnDifference);
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }
}
