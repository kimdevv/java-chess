package chess.domain.movement.policy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ColorPolicy implements Policy {

    private final Color color;

    public ColorPolicy(final Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(final Color color, final Position currentPosition, final Piece targetPiece) {
        return this.color == color;
    }
}
