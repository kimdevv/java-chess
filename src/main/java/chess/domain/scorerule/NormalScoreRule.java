package chess.domain.scorerule;

import chess.domain.position.Position;
import java.util.List;

public class NormalScoreRule extends ScoreRule {

    public NormalScoreRule(final double score) {
        super(score);
    }

    @Override
    public double findScore(final List<Position> positionPerPieces) {
        return positionPerPieces.size() * score;
    }
}
