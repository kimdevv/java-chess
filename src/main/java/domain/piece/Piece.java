package domain.piece;

import domain.Side;
import domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final Side side;
    protected final PieceScore score;

    public Piece(Side side, PieceScore score) {
        this.side = side;
        this.score = score;
    }

    public abstract boolean canMove(Position current, Position target, Map<Position, Piece> pieces);

    public void checkMovable(Position current, Position target, Map<Position, Piece> piecesOnPath) {
        validateNotSamePosition(current, target);
        checkBlockingPiece(target, piecesOnPath);
        checkMovementRule(current, target, piecesOnPath);
    }

    private void validateNotSamePosition(Position current, Position target) {
        if (current.equals(target)) {
            throw new IllegalArgumentException("source 위치와 target 위치가 같습니다.");
        }
    }

    private void checkBlockingPiece(Position target, Map<Position, Piece> pieces) {
        if (pieces.containsKey(target) && pieces.get(target).isSameSide(this)) {
            throw new IllegalArgumentException("target 위치에 같은 팀 기물이 존재합니다.");
        }
        List<Position> positionsExceptTarget = filterPositionsExceptTarget(target, pieces);
        if (positionsExceptTarget.size() > 0) {
            throw new IllegalArgumentException("target 위치로 이동하는 경로에 기물이 존재합니다.");
        }
    }

    private void checkMovementRule(Position current, Position target, Map<Position, Piece> piecesOnPath) {
        if (canMove(current, target, piecesOnPath)) {
            return;
        }
        throw new IllegalArgumentException("기물 이동 규칙에 맞지 않습니다.");
    }

    public boolean isBlack() {
        return side.isBlack();
    }

    public boolean isSameSide(Side other) {
        return side == other;
    }

    public boolean isSameSide(Piece other) {
        return side == other.side;
    }

    public boolean isNotSameSide(Side other) {
        return side != other;
    }

    public boolean isNotSameSide(Piece other) {
        return side != other.side;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isMajorPiece() {
        return true;
    }

    private List<Position> filterPositionsExceptTarget(Position target, Map<Position, Piece> pieces) {
        return pieces.keySet().stream()
                .filter(key -> key != target)
                .toList();
    }

    public double getScore() {
        return score.getScore();
    }

    public double decreaseScore() {
        throw new IllegalArgumentException("폰 외의 기물은 피스 점수를 변경할 수 없습니다.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Piece piece = (Piece) object;
        return side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
