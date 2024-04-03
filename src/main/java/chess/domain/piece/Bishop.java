package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import java.util.Set;

public class Bishop extends MultiStepPiece {
    public static final Bishop BLACK = new Bishop(Color.BLACK);
    public static final Bishop WHITE = new Bishop(Color.WHITE);

    private Bishop(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public Set<Direction> directions() {
        return Direction.DIAGONAL;
    }

    @Override
    public double score() {
        return 3;
    }
}
