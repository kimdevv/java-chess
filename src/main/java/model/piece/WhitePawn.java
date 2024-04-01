package model.piece;

import java.util.Set;
import model.game.Camp;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public final class WhitePawn extends Pawn {

    private static final int WHITE_PAWN_DELTA_ONE_ROW = -1;
    private static final int WHITE_PAWN_DELTA_TWO_ROW = -2;

    public WhitePawn() {
        super(Camp.WHITE);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
        }
        if (nextPosition.getRowIndex() - currentPosition.getRowIndex() == WHITE_PAWN_DELTA_ONE_ROW) {
            return Set.of();
        }
        return Set.of(new Position(currentPosition.getColumn(), Row.THREE));
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        return isPawnStraight(currentPosition, nextPosition);
    }

    private boolean isPawnStraight(final Position currentPosition, final Position nextPosition) {
        final int dRow = nextPosition.getRowIndex() - currentPosition.getRowIndex();
        final int dColumn = nextPosition.getColumnIndex() - currentPosition.getColumnIndex();
        if (dColumn != 0) {
            return false;
        }
        if (Row.TWO.getIndex() == currentPosition.getRowIndex() && dRow == WHITE_PAWN_DELTA_TWO_ROW) {
            return true;
        }
        return dRow == WHITE_PAWN_DELTA_ONE_ROW;
    }

    @Override
    public Set<Position> getAttackRoute(final Moving moving) {
        if (!canAttack(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
        }
        return Set.of();
    }

    private boolean canAttack(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        return isPawnDiagonal(currentPosition, nextPosition);
    }

    private static boolean isPawnDiagonal(final Position currentPosition, final Position nextPosition) {
        final int dRow = nextPosition.getRowIndex() - currentPosition.getRowIndex();
        final int dColumn = nextPosition.getColumnIndex() - currentPosition.getColumnIndex();
        if (Math.abs(dColumn) != 1) {
            return false;
        }
        return dRow == WHITE_PAWN_DELTA_ONE_ROW;
    }
}
