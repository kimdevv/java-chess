package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import java.util.Set;

public class Knight extends SingleStepPiece {
    public static final Knight WHITE = new Knight(Color.WHITE);
    public static final Knight BLACK = new Knight(Color.BLACK);

    private Knight(Color color) {
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
        return PieceType.KNIGHT;
    }

    @Override
    public Set<Direction> getDirections() {
        return Direction.KNIGHT;
    }

    @Override
    public double getScore() {
        return 2.5;
    }
}
