package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Bishop extends SlidingPiece {
    private static final Score BISHOP_SCORE = new Score(3);

    public Bishop(Team team) {
        super(team, BISHOP_SCORE);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (fileDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
