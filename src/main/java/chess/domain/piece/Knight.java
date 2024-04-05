package chess.domain.piece;

import chess.domain.route.Route;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return source.hasTwoFileGap(target) && source.hasOneRankGap(target) ||
                source.hasOneFileGap(target) && source.hasTwoRankGap(target);
    }

    @Override
    public double score(List<Piece> pieces) {
        return 2.5;
    }
}
