package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Direction;
import java.util.Set;

public class Rook extends MultiStepPiece {
    public static final Rook BLACK = new Rook(Color.BLACK);
    public static final Rook WHITE = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    @Override
    public Set<Direction> directions() {
        return Direction.STRAIGHT;
    }

    @Override
    public double score() {
        return 5;
    }
}
