package chess.domain.piece.moveRule.pawn;

import chess.domain.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.moveRule.MoveRule;
import chess.domain.position.Position;

public class BlackPawnMoveRule implements MoveRule {
    public static final int ONE_SQUARE = 1;
    private static BlackPawnMoveRule instance;
    private final Direction direction = Direction.MINUS;

    private BlackPawnMoveRule() {
    }

    public static BlackPawnMoveRule getInstance() {
        if (instance == null) {
            instance = new BlackPawnMoveRule();
        }
        return instance;
    }

    @Override
    public void validateMovement(Position currentPosition, Position nextPosition) {
        validateMoveToEmpty(nextPosition);
        if (isForwardDistanceMove(currentPosition, nextPosition, ONE_SQUARE)) {
            return;
        }
        if (isDiagonalMove(currentPosition, nextPosition)) {
            return;
        }
        throw new IllegalArgumentException("기물이 이동할 수 없는 위치입니다.");
    }

    private boolean isForwardDistanceMove(Position currentPosition, Position nextPosition, int distance) {
        Position forwardPosition = currentPosition.moveRank(direction, distance);
        return forwardPosition.equals(nextPosition);
    }

    private boolean isDiagonalMove(Position currentPosition, Position nextPosition) {
        Position leftDiagonal = currentPosition.move(Direction.MINUS, direction);
        Position rightDiagonal = currentPosition.move(Direction.PLUS, direction);
        return leftDiagonal.equals(nextPosition) || rightDiagonal.equals(nextPosition);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
}
