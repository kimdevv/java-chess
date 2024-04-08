package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.piece.obstaclefinder.ObstacleFinder;
import chess.domain.piece.obstaclefinder.PawnObstacleFinder;
import chess.domain.position.Position;
import java.util.Map;

public class Pawn extends Piece {
    private final ObstacleFinder obstacleFinder;

    public Pawn(final Color color) {
        super(PieceType.PAWN, color);
        this.obstacleFinder = new PawnObstacleFinder();
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return pieceType.getMovements()
                .stream()
                .filter(movement -> movement.isSatisfied(color, source,
                        pieces.getOrDefault(target, Empty.getInstance())))
                .map(Movement::getDirection)
                .anyMatch(direction -> direction.canReach(source, target,
                        obstacleFinder.findObstacle(source, target, pieces)));
    }
}
