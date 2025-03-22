package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected Position position;

    public Piece(final Position position) {
        this.position = position;
    }

    public final void changePosition(final Position position) {
        this.position = position;
    }

    public abstract List<Position> calculateRouteToDestination(final Position destination);

    protected final List<Position> calculateDiagonalRoute(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) != Math.abs(columnDifference)) {
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

    protected final List<Position> calculateVerticalRoute(int rowDifference) {
        List<Position> route = new ArrayList<>();
        while (rowDifference != 0) {
            route.add(position.moveVertical(rowDifference));
            rowDifference = decreaseOneAbsoluteValue(rowDifference);
        }
        return route;
    }

    protected final List<Position> calculateHorizontalRoute(int columnDifference) {
        List<Position> route = new ArrayList<>();
        while (columnDifference != 0) {
            route.add(position.moveHorizontal(columnDifference));
            columnDifference = decreaseOneAbsoluteValue(columnDifference);
        }
        return route;
    }

    protected final int decreaseOneAbsoluteValue(final int number) {
        if (number < 0) {
            return number + 1;
        }
        return number - 1;
    }

    public boolean isAt(final Position position) {
        return this.position.equals(position);
    }

    public Position getPosition() {
        return position;
    }

    public abstract boolean isKing();
}
