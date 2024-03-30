package chess.domain.chesspiece;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    private static final Score KNIGHT_SCORE = new Score(2.5);

    public Knight(Team team) {
        super(team, KNIGHT_SCORE);
    }

    @Override
    public List<Position> findRoute(Position source, Position target, boolean isEmpty) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int colDistance = source.calculateRankDistance(target);

        if (!((fileDistance == 2 && colDistance == 1) || (fileDistance == 1 && colDistance == 2))) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Score calculateScore(Score score, boolean hasSameFilePawn) {
        return score.sum(getScore());
    }
}
