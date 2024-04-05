package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.route.Route;
import java.util.List;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.isSameFile(target) || source.isSameRank(target) || source.isDiagonal(target);
    }

    @Override
    public double score(List<Piece> pieces) {
        return 9;
    }
}
