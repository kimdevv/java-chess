package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.route.Route;
import java.util.List;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty();

    public Empty() {
        super(Side.EMPTY);
    }

    public static Empty instance() {
        return INSTANCE;
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return false;
    }

    @Override
    public double score(List<Piece> pieces) {
        return 0;
    }
}
