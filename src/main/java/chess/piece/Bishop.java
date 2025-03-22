package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Position position) {
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
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }

    private List<Position> calculateDiagonalRoute(int rowDifference, int columnDifference) {
        if (rowDifference != columnDifference) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
        }
        List<Position> route = new ArrayList<>();
        while (rowDifference != 0) {
            route.add(position.moveDiagonal(rowDifference, columnDifference));
            rowDifference--;
            columnDifference--;
        }
        return route;
    }
}
