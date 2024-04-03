package domain.piece.pawn;

import domain.Direction;
import domain.Rank;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.util.Map;

public class WhitePawn extends Pawn {
    private static final Rank INITIAL_SQUARE = Rank.TWO;

    public WhitePawn() {
        super(Team.WHITE);
    }

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        if (pieces.containsKey(target)) {
            validateHasSameTeamPieceOnTarget(source, target, pieces);
            return source.next(Direction.NORTH_EAST).equals(target) ||
                    source.next(Direction.NORTH_WEST).equals(target);
        }
        return source.next(Direction.NORTH).equals(target) ||
                (source.next(Direction.NORTH_NORTH).equals(target) && source.isRank(INITIAL_SQUARE));
    }

}
