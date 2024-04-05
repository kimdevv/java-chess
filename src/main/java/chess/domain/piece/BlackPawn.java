package chess.domain.piece;

import chess.domain.game.InitialPosition;
import chess.domain.position.Position;
import chess.domain.route.Route;
import java.util.List;

public class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Side.BLACK);
    }

    @Override
    protected boolean isAttackable(Position source, Position target, Route route) {
        return isDownDiagonal(source, target) && route.isOpponentTargetPiece(Side.BLACK);
    }

    private boolean isDownDiagonal(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.hasOneFileGap(target);
    }

    @Override
    protected boolean isTwoForwardFromInitialPosition(Position source, Position target) {
        return isInitialPosition(source) && isDownTwo(source, target);
    }

    private boolean isInitialPosition(Position source) {
        List<Position> positions = InitialPosition.PAWN.positions(Side.BLACK);
        return positions.contains(source);
    }

    private boolean isDownTwo(Position source, Position target) {
        return source.hasHigherRankByTwo(target) && source.isSameFile(target);
    }

    @Override
    protected boolean isOneForward(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.isSameFile(target);
    }
}
