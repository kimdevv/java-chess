package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;
import score.Score;

public class Knight extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Color color) {
        super(color, new Score(KNIGHT_SCORE));
        this.pieceStrategy = new PieceStrategy(Direction.KNIGHT_DIRECTION);
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

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean isAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return pieceStrategy.findDirection(rowDifference, columnDifference);
    }
}
