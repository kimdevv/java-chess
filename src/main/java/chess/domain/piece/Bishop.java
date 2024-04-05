package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.isDiagonal(target);
    }

    @Override
    public double score(List<Piece> pieces) {
        return 3;
    }
}
