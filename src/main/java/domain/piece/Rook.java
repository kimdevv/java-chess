package domain.piece;

import domain.Side;
import domain.position.Position;

import java.util.Map;

public class Rook extends Piece {
    public Rook(Side side) {
        super(side, PieceScore.ROOK);
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
        return current.isSameRank(target) || current.isSameFile(target);
    }
}
