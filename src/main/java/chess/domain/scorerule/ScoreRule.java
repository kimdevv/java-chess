package chess.domain.scorerule;

import chess.domain.position.Position;
import java.util.List;

public abstract class ScoreRule {
    protected final double score;

    protected ScoreRule(final double score) {
        this.score = score;
    }

    public abstract double findScore(List<Position> positionPerPieces);
}
