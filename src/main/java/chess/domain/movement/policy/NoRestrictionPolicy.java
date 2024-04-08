package chess.domain.movement.policy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class NoRestrictionPolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final Position currentPosition, final Piece targetPiece) {
        return true;
    }
}
