package domain.piece.pawn;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;
import java.util.Set;
import score.Score;

public abstract class Pawn extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    private static final double PAWN_SCORE = 1;

    public Pawn(Color color, Set<Direction> directions) {
        super(color, new Score(PAWN_SCORE));
        this.pieceStrategy = new PieceStrategy(directions);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);
        validateDifference(rowDifference, columnDifference);

        if (canAttack) {
            pieceStrategy.findDirection(rowDifference, columnDifference);
        }
        return pieceStrategy.findDirection(divideValueByAbs(rowDifference), divideValueByAbs(columnDifference));
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    private int divideValueByAbs(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    private void validateDifference(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) + Math.abs(columnDifference) > 2) {
            throw new IllegalArgumentException("폰에게 가능한 방향이 없습니다.");
        }
    }

    public abstract boolean isFirstPosition(Coordinate coordinate);
}
