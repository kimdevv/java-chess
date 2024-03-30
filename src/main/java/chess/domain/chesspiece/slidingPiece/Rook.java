package chess.domain.chesspiece.slidingPiece;

import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Rook extends SlidingPiece {
    private static final Score ROOK_SCORE = new Score(5);

    public Rook(Team team) {
        super(team, ROOK_SCORE);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!source.isSameRank(target) && !source.isSameFile(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }
}
