package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import java.util.Set;

public class Knight extends SingleStepPiece {
    public static final Knight BLACK = new Knight(Color.BLACK);
    public static final Knight WHITE = new Knight(Color.WHITE);

    private Knight(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Set<Direction> directions() {
        return Direction.KNIGHT;
    }

    @Override
    public double score() {
        return 2.5;
    }
}
