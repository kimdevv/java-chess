package chess.domain.piece;

import chess.domain.point.Point;

import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    private final Type type;
    private final Team team;

    public Piece(final Type type, final Team team) {
        this.type = type;
        this.team = team;
    }

    public abstract boolean canMove(final Point departure, final Point destination, final Map<Point, Piece> board);

    protected abstract boolean isMovablePoint(final Point departure, final Point destination);

    public boolean isTeamMatch(final Team team) {
        return this.team == team;
    }

    protected boolean isNotSamePoint(final Point departure, final Point destination) {
        return !departure.equals(destination);
    }

    protected boolean hasSameTeamPieceAtDestination(final Piece pieceAtDeparture, final Piece pieceAtDestination) {
        return pieceAtDeparture.team == pieceAtDestination.team;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public double getScore() {
        return type.getScore();
    }

    public Type getType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
