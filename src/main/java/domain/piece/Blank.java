package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import score.Score;

public class Blank extends ChessPieceBase {

    public Blank() {
        super(Color.NONE, new Score(0));
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        throw new IllegalArgumentException("빈 칸으로, 움직일 방향이 없습니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
