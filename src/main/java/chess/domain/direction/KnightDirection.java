package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import chess.domain.position.Vector;
import java.util.Arrays;
import java.util.List;

public class KnightDirection implements Direction {

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return getPossiblePosition(source)
                .stream()
                .filter(position -> !obstacle.isBlocked(position, target))
                .anyMatch(position -> position.equals(target));
    }

    private List<Position> getPossiblePosition(final Position position) {
        return Arrays.stream(Vector.values())
                .filter(position::isNextPositionInRange)
                .map(vector -> Position.of(position.file().move(vector.getFileVector()),
                        position.rank().move(vector.getRankVector())))
                .toList();
    }
}

