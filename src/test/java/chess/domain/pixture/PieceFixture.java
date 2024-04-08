package chess.domain.pixture;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum PieceFixture {
    BLACK_PAWN(new Pawn(Color.BLACK)),
    WHITE_PAWN(new Pawn(Color.WHITE)),

    BLACK_KNIGHT(new Knight(Color.BLACK)),
    WHITE_KNIGHT(new Knight(Color.WHITE)),

    BLACK_QUEEN(new Queen(Color.BLACK)),
    WHITE_QUEEN(new Queen(Color.WHITE)),

    BLACK_BISHOP(new Bishop(Color.BLACK)),
    WHITE_BISHOP(new Bishop(Color.WHITE)),

    BLACK_ROOK(new Rook(Color.BLACK)),
    WHITE_ROOK(new Rook(Color.WHITE)),

    BLACK_KING(new King(Color.BLACK)),
    WHITE_KING(new King(Color.WHITE)),
    ;

    private final Piece piece;

    PieceFixture(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
