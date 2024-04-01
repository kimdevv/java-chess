package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import java.util.Set;

public class Bishop extends MultiStepPiece {
    public static final Bishop WHITE = new Bishop(Color.WHITE);
    public static final Bishop BLACK = new Bishop(Color.BLACK);

    private Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public Set<Direction> getDirections() {
        return Direction.DIAGONAL;
    }

    @Override
    public double getScore() {
        return 3.0;
    }
}
