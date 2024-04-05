package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    public King(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return (source.isSameFile(target) || source.hasOneFileGap(target)) &&
                (source.isSameRank(target) || source.hasOneRankGap(target));
    }

    @Override
    public double score(List<Piece> pieces) {
        return 0;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
