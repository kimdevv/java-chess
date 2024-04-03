package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class SingleStepPiece extends Piece {
    protected SingleStepPiece(Color color) {
        super(color);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();

        directions().forEach(direction -> addMoves(currentPosition, board, direction, movablePositions));

        return movablePositions;
    }

    private void addMoves(Position currentPosition, Board board, Direction direction, Set<Position> movablePositions) {
        Position position = currentPosition;
        if (!position.canMoveNext(direction)) {
            return;
        }

        position = position.next(direction);
        Piece piece = board.findPieceByPosition(position);

        if (isNotSameColor(piece)) {
            movablePositions.add(position);
        }
    }
}
