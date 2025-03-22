package chess.piece;

import chess.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Position position) {
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
        List<Position> route = new ArrayList<>();
        if (Math.abs(rowDifference) == 2 && Math.abs(columnDifference) == 1) {
            Position middlePosition = position.moveVertical(rowDifference);
            route.add(middlePosition.moveDiagonal(decreaseOneAbsoluteValue(rowDifference), columnDifference));
            return route;
        }
        if (Math.abs(rowDifference) == 1 && Math.abs(columnDifference) == 2) {
            Position middlePosition = position.moveHorizontal(columnDifference);
            route.add(middlePosition.moveDiagonal(rowDifference, decreaseOneAbsoluteValue(columnDifference)));
            return route;
        }
        throw new IllegalArgumentException("해당 기물이 움직일 수 없는 위치입니다.");
    }
}
