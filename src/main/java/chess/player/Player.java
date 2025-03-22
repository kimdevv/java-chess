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

    public Piece findPiece (final Position position) {
        return pieces.findPieceAt(position);
    }

    public void changePiecePosition(final Position startPosition, final Position destination) {
        Piece piece = pieces.findPieceAt(startPosition);
        piece.changePosition(destination);
    }

    public Team getTeam() {
        return team;
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public boolean isExistPieceAt(final Position destination) {
        return pieces.isExistAt(destination);
    }

    public boolean isExistPieceAtRoute(final List<Position> route) {
        return route.stream()
                .anyMatch(this::isExistPieceAt);
    }

    public Piece removeAndGetPieceAt(final Position destination) {
        Piece removePiece = pieces.findPieceAt(destination);
        pieces.remove(removePiece);
        return removePiece;
    }
}
