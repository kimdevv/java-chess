package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private static final int DEFAULT_WHITE_RANK = 2;
    private static final int DEFAULT_BLACK_RANK = 7;
    public static final Pawn BLACK = new Pawn(Color.BLACK);
    public static final Pawn WHITE = new Pawn(Color.WHITE);

    private Pawn(Color color) {
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public Set<Direction> directions() {
        if (isWhite()) {
            return Direction.WHITE_PAWN;
        }
        return Direction.BLACK_PAWN;
    }

    @Override
    public double score() {
        return 1;
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions().forEach(direction -> {
            if (!currentPosition.canMoveNext(direction)) {
                return;
            }

            addForwardMoves(board, direction, currentPosition, movablePositions);
            addDiagonalMoves(board, direction, currentPosition, movablePositions);
        });

        return movablePositions;
    }

    private void addForwardMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (direction.isDiagonal()) {
            return;
        }

        Position nextPosition = position.next(direction);
        Piece firstPiece = board.findPieceByPosition(nextPosition);

        if (firstPiece.isEmpty()) {
            movablePositions.add(nextPosition);
            addMultipleForwardMoves(board, direction, movablePositions, position.getRank(), nextPosition);
        }
    }

    private void addMultipleForwardMoves(Board board, Direction direction, Set<Position> movablePositions,
                                         int currentRank, Position nextPosition) {
        if (!isStartingPosition(direction, currentRank)) {
            return;
        }

        Position nextNextPosition = nextPosition.next(direction);
        Piece secondPiece = board.findPieceByPosition(nextNextPosition);

        if (secondPiece.isEmpty()) {
            movablePositions.add(nextNextPosition);
        }
    }

    private void addDiagonalMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (!direction.isDiagonal()) {
            return;
        }

        Position nextPosition = position.next(direction);
        Piece piece = board.findPieceByPosition(nextPosition);

        if (isNotSameColor(piece) && !piece.isEmpty()) {
            movablePositions.add(nextPosition);
        }
    }

    private boolean isStartingPosition(Direction direction, int currentRank) {
        return (DEFAULT_WHITE_RANK == currentRank && direction == Direction.NORTH) ||
                (DEFAULT_BLACK_RANK == currentRank && direction == Direction.SOUTH);
    }
}
