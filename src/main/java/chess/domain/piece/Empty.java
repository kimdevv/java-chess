package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Map;

public class Empty extends Piece {
    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(PieceType.EMPTY, Color.NONE);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return false;
    }
}
