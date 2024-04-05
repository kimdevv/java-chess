package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public abstract class UnslidingPiece extends Piece {

    protected UnslidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    protected Set<Position> movablePositions(final Chessboard chessboard, final Set<Movement> movements) {
        return movements.stream()
                .map(movement -> movablePosition(chessboard, movement))
                .flatMap(Optional::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    private Optional<Position> movablePosition(final Chessboard chessboard, final Movement movement) {
        return position().after(movement)
                .filter(position -> isMovable(chessboard, position));
    }

    private boolean isMovable(final Chessboard chessboard, final Position position) {
        return chessboard.isEmpty(position) || isAttackable(chessboard, position);
    }
}
