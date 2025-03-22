package chess.piece;

import chess.position.Position;

import java.util.List;

public class King extends Piece {

    public King(final Position position) {
        super(position);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public List<Position> calculateRouteToDestination(final Position destination) {
        final int rowDifference = destination.calculateRowDifference(position);
        final int columnDifference = destination.calculateColumnDifference(position);
        return selectRouteFromDifferences(rowDifference, columnDifference);
    }

    private List<Position> selectRouteFromDifferences(final int rowDifference, final int columnDifference) {
        if (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 1) {
            return calculateDiagonalRoute(rowDifference, columnDifference);
        }
        if (Math.abs(rowDifference) == 1) {
            return calculateVerticalRoute(rowDifference);
        }
        if (Math.abs(columnDifference) == 1) {
            return calculateHorizontalRoute(columnDifference);
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }
}
