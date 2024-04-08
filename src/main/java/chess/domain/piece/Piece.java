package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces);

    public boolean isSameTeam(final Piece piece) {
        return color.isSameColor(piece.color);
    }

    public boolean isSameTeamColor(final Color color) {
        return color.isSameColor(this.color);
    }

    public boolean isEmpty() {
        return this == Empty.getInstance();
    }

    public boolean isSameType(final PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
