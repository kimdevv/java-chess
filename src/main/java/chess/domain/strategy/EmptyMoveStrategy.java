package chess.domain.strategy;

import chess.domain.pieceinfo.Position;

public class EmptyMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position currentPosition, Position newPosition) {
        return false;
    }
}
