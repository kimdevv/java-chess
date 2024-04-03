package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import java.util.Set;

public class King extends SingleStepPiece {
    public static final King BLACK = new King(Color.BLACK);
    public static final King WHITE = new King(Color.WHITE);

    private King(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }

    @Override
    public Set<Direction> directions() {
        return Direction.ALL;
    }

    @Override
    public double score() {
        return 0;
    }
}
