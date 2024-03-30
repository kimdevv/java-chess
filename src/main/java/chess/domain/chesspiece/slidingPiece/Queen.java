package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Queen extends SlidingPiece {
    private static final Score QUEEN_SCORE = new Score(9);

    public Queen(Team team) {
        super(team, QUEEN_SCORE);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (!source.isSameFile(target) && !source.isSameRank(target)
                && fileDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
