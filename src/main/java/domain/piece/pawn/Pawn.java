package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.Position;
import domain.position.Rank;

public abstract class Pawn implements Piece {
    private static final int DEFAULT_MOVE_COUNT_LIMIT = 1;
    private static final int INITIAL_MOVE_COUNT_LIMIT = 2;

    protected Pawn() {
    }

    @Override
    public void validateMovement(Position source, Position target, Color targetColor) {
        source.validateDifferentPosition(target);
        if (isMovedBack(source, target)) {
            throw new IllegalArgumentException("Pawn은 뒤로 이동할 수 없습니다.");
        }
        validateDirection(source, target, targetColor);
    }

    private void validateDirection(Position source, Position target, Color targetColor) {
        if (source.isVerticalDirectionTo(target)) {
            validateVerticalDirection(source, target, targetColor);
            return;
        }
        if (source.isDiagonalDirectionTo(target)) {
            validateDiagonalDirection(source, target, targetColor);
            return;
        }
        throw new IllegalArgumentException("Pawn은 대각선 또는 앞으로 이동해야 합니다.");
    }

    private void validateVerticalDirection(Position source, Position target, Color targetColor) {
        int moveCount = source.calculateDistance(target);
        if (source.isSameRank(initialRank())) {
            validateInitialMoveCount(moveCount);
            validateNeutralityPiece(targetColor);
            return;
        }
        validateDefaultMoveCount(moveCount);
        validateNeutralityPiece(targetColor);
    }

    private void validateInitialMoveCount(int moveCount) {
        if (moveCount > INITIAL_MOVE_COUNT_LIMIT) {
            throw new IllegalArgumentException(
                    String.format("Pawn은 초기 칸에서 최대 %d칸 이동할 수 있습니다.", INITIAL_MOVE_COUNT_LIMIT));
        }
    }

    private void validateDefaultMoveCount(int moveCount) {
        if (moveCount != DEFAULT_MOVE_COUNT_LIMIT) {
            throw new IllegalArgumentException(
                    String.format("Pawn은 한 번에 %d칸 이동할 수 있습니다.", DEFAULT_MOVE_COUNT_LIMIT));
        }
    }

    private void validateNeutralityPiece(Color targetColor) {
        if (targetColor.isNotNeutrality()) {
            throw new IllegalArgumentException("이동하려는 칸에 기물이 존재합니다.");
        }
    }

    private void validateDiagonalDirection(Position source, Position target, Color targetColor) {
        int moveCount = source.calculateDistance(target);
        validateDefaultMoveCount(moveCount);
        if (isNotOppositeColor(targetColor)) {
            throw new IllegalArgumentException("대각선 방향으로 이동은 상대 기물이 있을 때만 가능합니다.");
        }
    }

    protected abstract boolean isMovedBack(Position source, Position target);

    protected abstract Rank initialRank();

    @Override
    public Type type() {
        return Type.PAWN;
    }
}
