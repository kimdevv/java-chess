package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import java.util.Set;

public class Rook extends MultiStepPiece {
    public static final Rook WHITE = new Rook(Color.WHITE);
    public static final Rook BLACK = new Rook(Color.BLACK);

    private Rook(Color color) {
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
        return PieceType.ROOK;
    }

    @Override
    public Set<Direction> getDirections() {
        return Direction.STRAIGHT;
    }

    @Override
    public double getScore() {
        return 5.0;
    }
}
