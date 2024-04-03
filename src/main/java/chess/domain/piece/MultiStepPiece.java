package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiStepPiece extends Piece {
    protected MultiStepPiece(Color color) {
        super(color);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions().forEach(direction -> addMoves(board, direction, currentPosition, movablePositions));

        return movablePositions;
    }

    private void addMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (!position.canMoveNext(direction)) {
            return;
        }

        Position nextPosition = position.next(direction);
        Piece piece = board.findPieceByPosition(nextPosition);

        if (isNotSameColor(piece)) {
            movablePositions.add(nextPosition);
        }

        if (piece.isEmpty()) {
            addMoves(board, direction, nextPosition, movablePositions);
        }
    }
}
