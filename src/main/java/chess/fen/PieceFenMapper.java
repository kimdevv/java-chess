package chess.fen;

import static chess.domain.piece.StartingPawn.startingPositions;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.piece.Bishop;
import chess.domain.piece.DefaultPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceConstructor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public enum PieceFenMapper {

    KING(King.class, King::new, 'k', 'K'),
    QUEEN(Queen.class, Queen::new, 'q', 'Q'),
    BISHOP(Bishop.class, Bishop::new, 'b', 'B'),
    KNIGHT(Knight.class, Knight::new, 'n', 'N'),
    ROOK(Rook.class, Rook::new, 'r', 'R'),
    PAWN(Pawn.class, PieceFenMapper::pawnOf, 'p', 'P'),
    ;

    private static final Set<Character> WHITE_PIECES = Arrays.stream(values())
            .map(piece -> piece.white)
            .collect(Collectors.toUnmodifiableSet());
    private static final Set<Character> BLACK_PIECES = Arrays.stream(values())
            .map(piece -> piece.black)
            .collect(Collectors.toUnmodifiableSet());

    private final Class<? extends Piece> pieceClass;
    private final PieceConstructor<? extends Piece> constructor;
    private final char black;
    private final char white;

    <P extends Piece> PieceFenMapper(
            final Class<P> pieceClass,
            final PieceConstructor<P> constructor,
            final char black,
            final char white
    ) {
        this.pieceClass = pieceClass;
        this.constructor = constructor;
        this.black = black;
        this.white = white;
    }

    public static Optional<Piece> pieceOf(final char fen, final int row, final int column) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.white == fen || pieceType.black == fen)
                .map(pieceType -> pieceType.create(colorFrom(fen), Position.of(row, column)))
                .findFirst();
    }

    public static char fenFrom(final Piece piece) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.isAssignableFrom(piece.getClass()))
                .map(pieceType -> pieceType.charOf(piece.color()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Color colorFrom(final char fen) {
        if (WHITE_PIECES.contains(fen)) {
            return Color.WHITE;
        }
        if (BLACK_PIECES.contains(fen)) {
            return Color.BLACK;
        }
        throw new IllegalArgumentException("FEN 형식에 맞지 않습니다: %c".formatted(fen));
    }

    private static Pawn pawnOf(final Color color, final Position position) {
        if (startingPositions(color).contains(position)) {
            return new StartingPawn(color, position);
        }
        return new DefaultPawn(color, position);
    }

    private Piece create(final Color color, final Position position) {
        return constructor.create(color, position);
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
