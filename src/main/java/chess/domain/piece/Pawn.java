package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.route.Route;
import java.util.List;

public abstract class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    protected abstract boolean isAttackable(Position source, Position target, Route route);

    protected abstract boolean isTwoForwardFromInitialPosition(Position source, Position target);

    protected abstract boolean isOneForward(Position source, Position target);

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        return isAttackable(source, target, route) ||
                isNotAttack(route) && (isTwoForwardFromInitialPosition(source, target) || isOneForward(source, target));
    }

    private boolean isNotAttack(Route route) {
        return route.isTargetPieceEmpty();
    }

    @Override
    public double score(List<Piece> pieces) {
        int sameSidePawnCount = countSameSidePawn(pieces);

        if (sameSidePawnCount > 1) {
            return 0.5;
        }
        return 1;
    }

    private int countSameSidePawn(List<Piece> pieces) {
        return (int) pieces.stream()
                .filter(this::equals)
                .count();
    }
}
