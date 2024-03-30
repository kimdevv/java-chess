package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class King extends SlidingPiece {
    private static final Score KING_SCORE = new Score(0);

    public King(Team team) {
        super(team, KING_SCORE);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (fileDistance != 1 && columnDistance != 1) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
