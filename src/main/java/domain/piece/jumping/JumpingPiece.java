package domain.piece.jumping;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public abstract class JumpingPiece extends Piece {
    protected JumpingPiece(final Team team) {
        super(team);
    }

    protected abstract List<Direction> movableDirections();

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        return movableDirections().stream()
                .filter(source::canMove)
                .map(source::next)
                .anyMatch(square -> square.equals(target) && !hasSameTeamPieceOnTarget(source, target, pieces));
    }
}
