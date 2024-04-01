package chess.domain.piece;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Piece {

    public static final Piece EMPTY_PIECE = new Piece(PieceType.EMPTY, Color.NONE);
    private static final List<Piece> CACHED_PIECES;
    static {
        List<Piece> pieces = new ArrayList<>(generateNormalPiece());
        pieces.add(new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        pieces.add(new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        CACHED_PIECES = Collections.unmodifiableList(pieces);
    }

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    private static List<Piece> generateNormalPiece() {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType != PieceType.EMPTY)
                .filter(pieceType -> pieceType != PieceType.WHITE_PAWN)
                .filter(pieceType -> pieceType != PieceType.BLACK_PAWN)
                .flatMap(pieceType -> Arrays.stream(Color.values()).map(color -> new Piece(pieceType, color)))
                .toList();
    }

    public static Piece of(final PieceType pieceType, final Color color) {
        return CACHED_PIECES.stream()
                .filter(piece -> piece.getPieceType() == pieceType && piece.getColor() == color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 타입과 색상으로 체스말을 생성할 수 없습니다."));
    }

    public boolean canMove(final Position source, final Position target, final Obstacle obstacle) {
        return pieceType.canMove(source, target, obstacle);
    }

    public boolean canAttack(final Position source, final Position target, final Obstacle obstacle) {
        return pieceType.canAttack(source, target, obstacle);
    }

    public boolean isEmpty() {
        return pieceType == PieceType.EMPTY;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
