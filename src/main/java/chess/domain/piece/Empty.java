package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Set;

public class Empty extends Piece {

    public Empty() {
        super(Color.NONE);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        throw new UnsupportedOperationException("비어 있는 칸은 움직일 수 없습니다.");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.NONE;
    }

    @Override
    public Set<Direction> getDirections() {
        return Collections.emptySet();
    }

    @Override
    public double getScore() {
        return 0;
    }
}
