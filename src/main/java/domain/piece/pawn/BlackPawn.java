package domain.piece.pawn;

import domain.Direction;
import domain.Rank;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.Map;

public class BlackPawn extends Pawn {
    private static final Rank INITIAL_SQUARE = Rank.SEVEN;

    public BlackPawn() {
        super(Team.BLACK);
    }

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        if (pieces.containsKey(target)) {
            validateHasSameTeamPieceOnTarget(source, target, pieces);
            return source.next(Direction.SOUTH_EAST).equals(target) ||
                    source.next(Direction.SOUTH_WEST).equals(target);
        }
        return source.next(Direction.SOUTH).equals(target) ||
                (source.next(Direction.SOUTH_SOUTH).equals(target) && source.isRank(INITIAL_SQUARE));
    }
}
