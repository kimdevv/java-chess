package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Set;

public class Empty extends Piece {
    public Empty() {
        super(Color.NONE);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.NONE;
    }

    @Override
    public Set<Direction> directions() {
        return Collections.emptySet();
    }

    @Override
    public double score() {
        return 0;
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        throw new UnsupportedOperationException("비어 있는 칸은 움직일 수 없습니다.");
    }
}
