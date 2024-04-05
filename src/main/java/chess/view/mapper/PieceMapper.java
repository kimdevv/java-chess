package chess.view.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Arrays;

public enum PieceMapper {

    ROOK(Rook.class, "r"),
    KNIGHT(Knight.class, "n"),
    BISHOP(Bishop.class, "b"),
    QUEEN(Queen.class, "q"),
    KING(King.class, "k"),
    BLACK_PAWN(BlackPawn.class, "P"),
    WHITE_PAWN(WhitePawn.class, "p"),
    EMPTY(Empty.class, "."),
    ;

    private final Class<? extends Piece> pieceClass;
    private final String text;

    PieceMapper(Class<? extends Piece> pieceClass, String text) {
        this.pieceClass = pieceClass;
        this.text = text;
    }

    public static String toText(Piece piece) {
        String text = Arrays.stream(values())
                .filter(it -> it.pieceClass == piece.getClass())
                .findAny()
                .map(it -> it.text)
                .orElseThrow(IllegalArgumentException::new);

        if (piece.isBlack()) {
            return text.toUpperCase();
        }
        return text;
    }
}
