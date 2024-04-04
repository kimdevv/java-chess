package domain.piece;

import domain.Side;
import domain.position.Position;

import java.util.Map;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side, PieceScore.PAWN);
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {

        if (current.isSameRank(target) || isReverseMove(current, target)) {
            throw new IllegalArgumentException("폰은 수평 혹은 뒤로 이동할 수 없습니다.");
        }
        if (current.isSameFile(target) && pieces.containsKey(target)) {
            throw new IllegalArgumentException("폰은 같은 선상의 적을 공격할 수 없습니다.");
        }
        if (isInitPosition(current) && current.hasOnlyTwoRankGap(target)) {
            return true;
        }
        if (current.hasOneDiagonalGap(target) && hasOpponentAtTarget(target, pieces)) {
            return true;
        }
        return current.hasOneRankGap(target) && current.isSameFile(target);
    }

    private boolean isReverseMove(Position current, Position target) {
        if (isBlack()) {
            return current.isRankIncreased(target);
        }
        return current.isRankDecreased(target);
    }

    private boolean isInitPosition(Position current) {
        if (isBlack()) {
            return current.isBlackPawnRank();
        }
        return current.isWhitePawnRank();
    }

    private boolean hasOpponentAtTarget(Position target, Map<Position, Piece> pieces) {
        return pieces.containsKey(target) && pieces.get(target).isNotSameSide(this);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isMajorPiece() {
        return false;
    }

    @Override
    public double decreaseScore() {
        return score.toHalfScore();
    }
}
