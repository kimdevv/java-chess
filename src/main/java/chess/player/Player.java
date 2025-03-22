package chess.player;

import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.PiecesInitializer;
import chess.position.Position;

import java.util.List;

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

    public List<Position> calculateRouteToDestinationFromStartPosition(final Position startPosition, final Position destination) {
        Piece piece = pieces.findPieceAt(startPosition);
        return piece.calculateRouteToDestination(destination);
    }

    public void changePiecePosition(final Position startPosition, final Position destination) {
        Piece piece = pieces.findPieceAt(startPosition);
        piece.changePosition(destination);
    }
}
