package chess.domain.piece.obstaclefinder;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class MovementDirectionObstacleFinder implements ObstacleFinder {
    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);
        obstacles.remove(source);

        removeCapturableTargetFromObstacle(target, pieces.getOrDefault(source, Empty.getInstance()),
                pieces.getOrDefault(target, Empty.getInstance()), obstacles);
        return obstacles;
    }
}
