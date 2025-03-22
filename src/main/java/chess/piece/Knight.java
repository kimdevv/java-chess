package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Position position) {
        super(position);
    }

    @Override
    public List<Position> calculateRouteToDestination(final Position destination) {
        final int rowDifference = destination.calculateRowDifference(position);
        final int columnDifference = destination.calculateColumnDifference(position);
        return selectRouteFromDifferences(rowDifference, columnDifference);
    }

    private List<Position> selectRouteFromDifferences(final int rowDifference, final int columnDifference) {
        if ((Math.abs(rowDifference) == 2 && Math.abs(columnDifference) == 1) && (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 2)) {
            return calculateDiagonalRoute(rowDifference, columnDifference);
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }

    private List<Position> calculateDiagonalRoute(int rowDifference, int columnDifference) {
        if (rowDifference != columnDifference) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
        }
        List<Position> route = new ArrayList<>();
        while (rowDifference != 0) {
            route.add(position.moveDiagonal(rowDifference, columnDifference));
            rowDifference = decreaseOneAbsoluteValue(rowDifference);
            columnDifference = decreaseOneAbsoluteValue(columnDifference);
        }
        return route;
    }

    private int decreaseOneAbsoluteValue(final int number) {
        if (number < 0) {
            return number + 1;
        }
        return number - 1;
    }
}
