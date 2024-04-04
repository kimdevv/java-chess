package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.ChessPieceBase;
import domain.piece.type.PieceType;

public class King extends ChessPieceBase {

    private static final double NONE = 0d;

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);
        Direction direction = getKingDirection(rowDifference, columnDifference);
        validateDistance(start, destination, direction);

        return direction;
    }

    @Override
    public double getDefaultScore() {
        return NONE;
    }

    private Direction getKingDirection(int rowDifference, int columnDifference) {
        if (DiagonalDirection.isDiagonal(rowDifference, columnDifference)) {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        }
        return StraightDirection.getDirection(rowDifference, columnDifference);
    }

    private void validateDistance(Coordinate start, Coordinate destination, Direction direction) {
        if (start.calculateDistanceToDestination(direction, destination) != 1) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }
}
