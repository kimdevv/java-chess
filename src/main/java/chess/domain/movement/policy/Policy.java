package chess.domain.movement.policy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public interface Policy {

    boolean isSatisfied(final Color color, final Position currentPosition, final Piece piece);
}
