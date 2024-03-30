package chess.domain.chesspiece.pawn;

import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Score;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class WhitePawn extends Pawn {
    private static final Rank WHITE_PAWN_START_COLUMN = Rank.TWO;

    public WhitePawn() {
        super(WHITE);
    }

    @Override
    protected int calculatePawnRankDistance(Position source, Position target) {
        return target.subtractRanks(source);
    }

    @Override
    protected boolean isStartPosition(Position source) {
        return source.getRank() == WHITE_PAWN_START_COLUMN;
    }
}
