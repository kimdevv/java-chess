package chess.domain.chesspiece;

import chess.domain.chessboard.Numbering;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPiece {

    private static final Map<String, ChessPiece> CHESS_PIECE_CACHE = generate();

    private final Camp camp;
    private final ChessPieceProperty chessPieceProperty;

    private ChessPiece(Camp camp, ChessPieceProperty chessPieceProperty) {
        this.camp = camp;
        this.chessPieceProperty = chessPieceProperty;
    }

    public static ChessPiece of(Camp camp, ChessPieceProperty chessPieceProperty) {
        return CHESS_PIECE_CACHE.get(toKey(camp, chessPieceProperty));
    }

    private static Map<String, ChessPiece> generate() {
        List<ChessPiece> createdChessPiece = createChessPiece();
        Map<String, ChessPiece> chessPieceCache = new HashMap<>();
        createdChessPiece.forEach(chessPiece ->
                chessPieceCache.put(toKey(chessPiece.getCamp(), chessPiece.getChessPieceProperty()), chessPiece));
        return chessPieceCache;
    }

    private static String toKey(Camp camp, ChessPieceProperty chessPieceProperty) {
        return camp.name() + chessPieceProperty.getChessPieceType().name();
    }

    private static List<ChessPiece> createChessPiece() {
        return createChessPieceCamp().stream()
                .flatMap(createdCamp -> createChessPieceProperty().stream()
                        .map(property -> new ChessPiece(createdCamp, property)))
                .toList();
    }

    private static List<Camp> createChessPieceCamp() {
        return Arrays.stream(Numbering.values())
                .map(ChessPiece::determineCamp)
                .toList();
    }

    public static Camp determineCamp(Numbering numbering) {
        if (numbering.isWhiteCampNumbering()) {
            return Camp.WHITE;
        }
        if (numbering.isBlackCampNumbering()) {
            return Camp.BLACK;
        }
        return Camp.NONE;
    }

    private static List<ChessPieceProperty> createChessPieceProperty() {
        return Arrays.stream(ChessPieceType.values())
                .map(chessPieceType -> new ChessPieceProperty(chessPieceType, chessPieceType.getMoveStrategy()))
                .toList();
    }

    public ChessPieceType getChessPieceType() {
        return chessPieceProperty.getChessPieceType();
    }

    public boolean isEmptyChessPiece() {
        return getChessPieceType().isChessPieceTypeNone();
    }

    public boolean isChessPiecePawn() {
        return getChessPieceType().isChessPieceTypePawn();
    }

    public boolean isChessPieceKing() {
        return getChessPieceType().isChessPieceTypeKing();
    }

    public boolean isChessPieceSameCamp(ChessPiece chessPiece) {
        return getCamp() == chessPiece.getCamp();
    }

    public Camp getCamp() {
        return camp;
    }

    public ChessPieceProperty getChessPieceProperty() {
        return chessPieceProperty;
    }
}
