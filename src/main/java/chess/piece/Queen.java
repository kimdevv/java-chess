package chess.piece;

import chess.position.Position;

import java.util.List;

public class Queen extends Piece {

    public Queen(final Position position) {
        super(position);
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
        if (rowDifference != 0 && columnDifference != 0) {
            return calculateDiagonalRoute(rowDifference, columnDifference);
        }
        if (rowDifference != 0) {
            return calculateVerticalRoute(rowDifference);
        }
        if (columnDifference != 0) {
            return calculateHorizontalRoute(columnDifference);
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }
}
