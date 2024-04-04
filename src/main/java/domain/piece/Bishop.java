package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.type.PieceType;

public class Bishop extends ChessPieceBase {

    private static final double BISHOP_DEFAULT_SCORE = 3d;

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return DiagonalDirection.getDirection(rowDifference, columnDifference);
    }

    @Override
    public double getDefaultScore() {
        return BISHOP_DEFAULT_SCORE;
    }
}
