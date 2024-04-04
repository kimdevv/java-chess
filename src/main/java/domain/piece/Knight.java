package domain.piece;

import domain.Side;
import domain.position.Position;

import java.util.Map;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side, PieceScore.KNIGHT);
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
        return (current.hasTwoFileGap(target) && current.hasOneRankGap(target)) ||
                (current.hasOneFileGap(target) && current.hasTwoRankGap(target));
    }
}
