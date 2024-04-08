package chess.domain.piece.obstaclefinder;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class PawnObstacleFinder implements ObstacleFinder {

    @Override
    public List<Position> findObstacle(final Position source, final Position target,
                                       final Map<Position, Piece> pieces) {
        List<Position> obstacles = getNotEmptyPiecePositions(pieces);
        obstacles.remove(source);

        Piece sourcePiece = pieces.getOrDefault(source, Empty.getInstance());
        Piece targetPiece = pieces.getOrDefault(target, Empty.getInstance());
        removeCapturableTargetFromObstacle(target, sourcePiece, targetPiece, obstacles);
        addTargetToObstacle(source, target, sourcePiece, targetPiece, obstacles);
        return obstacles;
    }

    private void addTargetToObstacle(final Position source, final Position target,
                                     final Piece sourcePiece, final Piece targetPiece, final List<Position> obstacles) {
        if (canNotKillTargetByStraightMove(source, target, sourcePiece, targetPiece)
                || canNotMoveToTargetByDiagonalMove(source, target, targetPiece)) {
            obstacles.add(target);
        }
    }

    // 도착 위치가 상대편 말이지만 출발 위치와 도착 위치가 직선 이동일 경우
    private boolean canNotKillTargetByStraightMove(final Position source, final Position target,
                                                   final Piece sourcePiece, final Piece targetPiece) {
        return !targetPiece.isSameTeam(sourcePiece) &&
                !targetPiece.isEmpty() &&
                source.isRankMove(target);
    }

    // 도착 위치가 비어있고 출발 위치와 도착 위치가 대각 이동일 경우
    private boolean canNotMoveToTargetByDiagonalMove(final Position source, final Position target,
                                                     final Piece targetPiece) {
        return targetPiece.isEmpty()
                && source.isDiagonalBy(target);
    }
}
