package chess.domain.piece;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> movablePositions(final Chessboard chessboard, final Set<Direction> directions) {
        return directions.stream()
                .map(direction -> movablePositionsOf(chessboard, direction, position()))
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    private Set<Position> movablePositionsOf(
            final Chessboard chessboard,
            final Direction direction,
            final Position position
    ) {
        Optional<Position> possiblePosition = position.moveTo(direction);
        if (possiblePosition.isEmpty()) {
            return Set.of();
        }
        Position nextPosition = possiblePosition.get();
        return concatMovablePositions(chessboard, direction, nextPosition);
    }

    private Set<Position> concatMovablePositions(
            final Chessboard chessboard,
            final Direction direction,
            final Position position
    ) {
        if (chessboard.isEmpty(position)) {
            Set<Position> positions = new HashSet<>(movablePositionsOf(chessboard, direction, position));
            positions.add(position);
            return positions;
        }
        if (isAttackable(chessboard, position)) {
            return Set.of(position);
        }
        return Set.of();
    }
}
