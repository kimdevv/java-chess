package chess.domain.piece.obstaclefinder;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class KnightObstacleFinder implements ObstacleFinder {

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.getOrDefault(target, Empty.getInstance());

        if (sourcePiece.isSameTeamColor(targetPiece.getColor())) {
            return List.of(target);
        }
        return List.of();
    }
}
