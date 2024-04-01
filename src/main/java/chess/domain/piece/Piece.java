package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.Set;

public abstract class Piece {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract Set<Position> calculateMovablePositions(Position currentPosition, Board board);

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isEmpty() {
        return getPieceType() == PieceType.NONE;
    }

    public boolean isNotSameColor(Piece otherPiece) {
        return !color.isSame(otherPiece.color);
    }

    public boolean isSameColor(Color other) {
        return color.isSame(other);
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public abstract PieceType getPieceType();

    public abstract Set<Direction> getDirections();

    public abstract double getScore();

    public Color getColor() {
        return color;
    }
}
