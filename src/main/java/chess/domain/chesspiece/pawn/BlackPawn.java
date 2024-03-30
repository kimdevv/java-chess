package chess.domain.chesspiece.pawn;

import static chess.domain.chesspiece.Team.BLACK;

import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class BlackPawn extends Pawn {
    private static final Rank BLACK_PAWN_START_COLUMN = Rank.SEVEN;

    public BlackPawn() {
        super(BLACK);
    }


    @Override
    protected int calculatePawnRankDistance(Position source, Position target) {
        return source.subtractRanks(target);
    }

    @Override
    protected boolean isStartPosition(Position source) {
        return source.getRank() == BLACK_PAWN_START_COLUMN;
    }
}
