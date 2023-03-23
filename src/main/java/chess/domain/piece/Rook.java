package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;
import chess.domain.piece.moveRule.RookMoveRule;
import chess.domain.position.Position;

public class Rook extends AbstractPiece {
    private Rook(
            MoveRule moveRule, Color color) {
        super(moveRule, color);
    }

    public static Rook from(Color color) {
        return new Rook(RookMoveRule.getInstance(), color);
    }

    @Override
    public Piece move(Position currentPosition, Position nextPosition, Piece pieceOfNextPosition) {
        validateSameColor(pieceOfNextPosition);
        validateMovement(currentPosition, nextPosition);
        return this;
    }

    @Override
    public boolean isSliding() {
        return true;
    }
}
