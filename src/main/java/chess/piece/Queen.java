package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(final Position position) {
        super(position);
    }

    @Override
    public List<Position> calculateRouteToDestination(final Position destination) {
        final int rowDifference = destination.calculateRowDifference(position);
        final int columnDifference = destination.calculateColumnDifference(position);
        return selectRouteFromDifferences(rowDifference, columnDifference);
    }

    private List<Position> selectRouteFromDifferences(final int rowDifference, final int columnDifference) {
        if (rowDifference == columnDifference && rowDifference != 0) {
            return calculateDiagonalRoute(rowDifference, columnDifference);
        }
        if (rowDifference != 0 && columnDifference == 0) {
            return calculateVerticalRoute(rowDifference);
        }
        if (rowDifference == 0 && columnDifference != 0) {
            return calculateHorizontalRoute(columnDifference);
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }

    private List<Position> calculateDiagonalRoute(int rowDifference, int columnDifference) {
        if (rowDifference != columnDifference) {
            throw new IllegalArgumentException("잘못된 이동 요청입니다.");
        }
        List<Position> route = new ArrayList<>();
        while (rowDifference != 0) {
            route.add(position.moveDiagonal(rowDifference, columnDifference));
            rowDifference--;
            columnDifference--;
        }
        return route;
    }

    private List<Position> calculateVerticalRoute(int rowDifference) {
        List<Position> route = new ArrayList<>();
        while (rowDifference != 0) {
            route.add(position.moveVertical(rowDifference));
            rowDifference--;
        }
        return route;
    }

    private List<Position> calculateHorizontalRoute(int columnDifference) {
        List<Position> route = new ArrayList<>();
        while (columnDifference != 0) {
            route.add(position.moveHorizontal(columnDifference));
            columnDifference--;
        }
        return route;
    }
}
