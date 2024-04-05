package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;
import score.Score;

public class Rook extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, new Score(ROOK_SCORE));
        this.pieceStrategy = new PieceStrategy(Direction.STRAIGHT_DIRECTION);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return pieceStrategy.findDirection(divideValueByAbs(rowDifference), divideValueByAbs(columnDifference));
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    private int divideValueByAbs(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }
}
