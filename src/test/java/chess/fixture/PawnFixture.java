package chess.fixture;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;

public class PawnFixture {

    public static Pawn 흰색_폰() {
        return new Pawn(Team.WHITE);
    }

    public static Pawn 검정_폰() {
        return new Pawn(Team.BLACK);
    }
}
