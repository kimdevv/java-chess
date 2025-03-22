package chess.piece;

import chess.position.Position;

import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceAt(final Position startPosition) {
        return pieces.stream()
                .filter(piece -> piece.isAt(startPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean isExistAt(final Position destination) {
        return pieces.stream()
                .anyMatch(piece -> piece.isAt(destination));
    }

    public void remove(final Piece removePiece) {
        pieces.remove(removePiece);
    }
}
