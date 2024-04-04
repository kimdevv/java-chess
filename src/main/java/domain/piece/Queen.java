package domain.piece;

import domain.Side;
import domain.position.Position;

import java.util.Map;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side, PieceScore.QUEEN);
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
        return current.isDiagonal(target) || current.isSameFile(target) || current.isSameRank(target);
    }
}
