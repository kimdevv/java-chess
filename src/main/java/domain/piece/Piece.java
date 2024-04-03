package domain.piece;

import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    protected abstract List<Direction> movableDirections();

    public boolean cannotMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return !canMove(source, target, pieces);
    }

    protected abstract boolean canMove(Square source, Square target, Map<Square, Piece> pieces);


    protected boolean hasSameTeamPieceOnTarget(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return pieces.containsKey(target) && pieces.get(target).isSameTeam(pieces.get(source));
    }

    public boolean isSameTeam(final Piece other) {
        return this.team == other.team;
    }

    public boolean isOppositeTeam(final Team other) {
        return team != other;
    }

    public boolean isBlack() {
        return this.team == Team.BLACK;
    }

    public boolean isPawn() {
        return false;
    }

    public Team team() {
        return team;
    }

    public abstract double getScore(Map<Square, Piece> pieces, Square square);

    public boolean isKing() {
        return false;
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
        return Objects.hash(team, getClass().getSimpleName());
    }
}
