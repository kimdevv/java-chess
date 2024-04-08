package chess.domain.scorerule;

import chess.domain.position.Position;
import java.util.List;

public class NoScoreRule extends ScoreRule {
    public NoScoreRule() {
        super(0);
    }

    @Override
    public double findScore(final List<Position> positionPerPieces) {
        return 0;
    }
}
