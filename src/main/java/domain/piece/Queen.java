package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.ChessPieceBase;
import domain.piece.type.PieceType;

public class Queen extends ChessPieceBase {

    private static final double QUEEN_DEFAULT_SCORE = 9d;

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        if (DiagonalDirection.isDiagonal(rowDifference, columnDifference)) {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        }
        return StraightDirection.getDirection(rowDifference, columnDifference);
    }

    @Override
    public double getDefaultScore() {
        return QUEEN_DEFAULT_SCORE;
    }
}
