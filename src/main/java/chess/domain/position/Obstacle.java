package chess.domain.position;

import java.util.List;

public class Obstacle {
    
    private final List<Position> obstacles;

    public Obstacle(final List<Position> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean isBlocked(final Position position, final Position target) {
        if (position.equals(target)) {
            return false;
        }
        return obstacles.contains(position);
    }
}
