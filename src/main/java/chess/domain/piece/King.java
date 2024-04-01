package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import java.util.Set;

public class King extends SingleStepPiece {
    public static final King WHITE = new King(Color.WHITE);
    public static final King BLACK = new King(Color.BLACK);

    private King(Color color) {
        super(color);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public Set<Direction> getDirections() {
        return Direction.ALL;
    }

    @Override
    public double getScore() {
        return 0;
    }
}
