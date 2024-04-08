package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.piece.obstaclefinder.KnightObstacleFinder;
import chess.domain.piece.obstaclefinder.ObstacleFinder;
import chess.domain.position.Position;
import java.util.Map;

public class Knight extends Piece {
    private final ObstacleFinder obstacleFinder;

    public Knight(final Color color) {
        super(PieceType.KNIGHT, color);
        this.obstacleFinder = new KnightObstacleFinder();
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source, pieces.get(target)))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target,
                        obstacleFinder.findObstacle(source, target, pieces)));
    }
}
