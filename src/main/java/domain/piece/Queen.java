package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;
import score.Score;

public class Queen extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, new Score(QUEEN_SCORE));
        this.pieceStrategy = new PieceStrategy(Direction.ALL_DIRECTION);
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
