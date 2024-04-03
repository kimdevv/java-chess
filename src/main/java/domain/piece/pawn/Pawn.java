package domain.piece.pawn;

import domain.Direction;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.List;
import java.util.Map;

public abstract class Pawn extends Piece {
    private static final int SCORE = 1;

    Pawn(final Team team) {
        super(team);
    }

    @Override
    protected List<Direction> movableDirections() {
        return List.of();
    }

    public abstract boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces);

    protected void validateHasSameTeamPieceOnTarget(final Square source, final Square target, final Map<Square, Piece> pieces) {
        final Piece targetPiece = pieces.get(target);
        final Piece sourcePiece = pieces.get(source);

        if (targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("같은 팀은 공격할 수 없습니다.");
        }
    }

    @Override
    public double getScore(final Map<Square, Piece> pieces, final Square square) {
        final long count = pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .filter(entry -> entry.getValue().isSameTeam(this))
                .filter(entry -> entry.getKey().isSameFile(square))
                .count();
        if (count > 1) {
            return 0.5;
        }
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
