package chess.domain.strategy;

import chess.domain.pieceinfo.Position;

public interface MoveStrategy {
    boolean canMove(Position currentPosition, Position newPosition);
}
