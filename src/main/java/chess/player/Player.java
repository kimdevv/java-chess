package chess.player;

import chess.piece.Pieces;
import chess.piece.PiecesInitializer;

public class Player {

    private final Team team;
    private final Pieces pieces;

    private Player(final Team team) {
        this.team = team;
        this.pieces = PiecesInitializer.initialize(team);
    }

    public static Player generateWithPiecesFromTeam(final Team team) {
        return new Player(team);
    }
}
