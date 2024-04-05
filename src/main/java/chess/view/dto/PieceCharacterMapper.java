package chess.view.dto;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;

public enum PieceCharacterMapper {

    KING(King.class, 'k', 'K'),
    QUEEN(Queen.class, 'q', 'Q'),
    BISHOP(Bishop.class, 'b', 'B'),
    KNIGHT(Knight.class, 'n', 'N'),
    ROOK(Rook.class, 'r', 'R'),
    PAWN(Pawn.class, 'p', 'P');

    private final Class<? extends Piece> pieceClass;
    private final char white;
    private final char black;

    <P extends Piece> PieceCharacterMapper(final Class<P> pieceClass, final char white, final char black) {
        this.pieceClass = pieceClass;
        this.white = white;
        this.black = black;
    }

    public static char toCharacter(final Piece piece) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.isAssignableFrom(piece.getClass()))
                .map(character -> character.charOf(piece.color()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private <P extends Piece> boolean isAssignableFrom(final Class<P> other) {
        return pieceClass.isAssignableFrom(other);
    }

    private char charOf(final Color color) {
        if (color.isBlack()) {
            return black;
        }
        return white;
    }
}
