package chess.domain.piece;


import chess.domain.Color;
import chess.domain.Direction;
import java.util.Set;

public class Queen extends MultiStepPiece {
    public static final Queen WHITE = new Queen(Color.WHITE);
    public static final Queen BLACK = new Queen(Color.BLACK);

    private Queen(Color color) {
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
        return PieceType.QUEEN;
    }

    @Override
    public Set<Direction> getDirections() {
        return Direction.ALL;
    }

    @Override
    public double getScore() {
        return 9.0;
    }
}
