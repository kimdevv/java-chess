package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import java.util.Set;

public class Queen extends MultiStepPiece {
    public static final Queen BLACK = new Queen(Color.BLACK);
    public static final Queen WHITE = new Queen(Color.WHITE);

    private Queen(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public Set<Direction> directions() {
        return Direction.ALL;
    }

    @Override
    public double score() {
        return 9;
    }
}
