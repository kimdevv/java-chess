package chess.domain.piece;

import chess.domain.game.InitialPosition;
import chess.domain.position.Position;
import chess.domain.route.Route;
import java.util.List;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Side.WHITE);
    }

    @Override
    protected boolean isAttackable(Position source, Position target, Route route) {
        return isUpDiagonal(source, target) && route.isOpponentTargetPiece(Side.WHITE);
    }

    private boolean isUpDiagonal(Position source, Position target) {
        return target.hasHigherRankByOne(source) && source.hasOneFileGap(target);
    }

    @Override
    protected boolean isTwoForwardFromInitialPosition(Position source, Position target) {
        return isInitialPosition(source) && isUpTwo(source, target);
    }

    private boolean isInitialPosition(Position source) {
        List<Position> positions = InitialPosition.PAWN.positions(Side.WHITE);
        return positions.contains(source);
    }

    private boolean isUpTwo(Position source, Position target) {
        return target.hasHigherRankByTwo(source) && source.isSameFile(target);
    }

    @Override
    protected boolean isOneForward(Position source, Position target) {
        return target.hasHigherRankByOne(source) && source.isSameFile(target);
    }
}
