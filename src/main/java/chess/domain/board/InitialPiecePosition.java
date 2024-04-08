package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public enum InitialPiecePosition {
    BLACK_KING(new King(Color.BLACK), List.of(Position.of(5, 8))),
    WHITE_KING(new King(Color.WHITE), List.of(Position.of(5, 1))),

    BLACK_QUEEN(new Queen(Color.BLACK), List.of(Position.of(4, 8))),
    WHITE_QUEEN(new Queen(Color.WHITE), List.of(Position.of(4, 1))),

    BLACK_BISHOP(new Bishop(Color.BLACK), List.of(
            Position.of(3, 8),
            Position.of(6, 8))),
    WHITE_BISHOP(new Bishop(Color.WHITE), List.of(
            Position.of(3, 1),
            Position.of(6, 1))),

    BLACK_ROOK(new Rook(Color.BLACK), List.of(
            Position.of(1, 8),
            Position.of(8, 8))),
    WHITE_ROOK(new Rook(Color.WHITE), List.of(
            Position.of(1, 1),
            Position.of(8, 1))),

    BLACK_KNIGHT(new Knight(Color.BLACK), List.of(
            Position.of(2, 8),
            Position.of(7, 8))),
    WHITE_KNIGHT(new Knight(Color.WHITE), List.of(
            Position.of(2, 1),
            Position.of(7, 1))),

    BLACK_PAWN(new Pawn(Color.BLACK), List.of(
            Position.of(1, 7),
            Position.of(2, 7),
            Position.of(3, 7),
            Position.of(4, 7),
            Position.of(5, 7),
            Position.of(6, 7),
            Position.of(7, 7),
            Position.of(8, 7))),

    WHITE_PAWN(new Pawn(Color.WHITE), List.of(
            Position.of(1, 2),
            Position.of(2, 2),
            Position.of(3, 2),
            Position.of(4, 2),
            Position.of(5, 2),
            Position.of(6, 2),
            Position.of(7, 2),
            Position.of(8, 2)));

    private final Piece piece;
    private final List<Position> initialPositions;

    InitialPiecePosition(final Piece piece, final List<Position> initialPositions) {
        this.piece = piece;
        this.initialPositions = initialPositions;
    }

    public static List<InitialPiecePosition> getInitialPositionByColor(Color color) {
        return Arrays.stream(values())
                .filter(initialPiecePosition -> initialPiecePosition.isSameColor(color))
                .toList();
    }

    public boolean isSameColor(final Color color) {
        return color.isSameColor(piece.getColor());
    }

    public List<Position> getInitialPositions() {
        return initialPositions;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isPawnFirstMove(final Position position) {
        return piece.isSameType(PieceType.PAWN) &&
                initialPositions.contains(position);
    }
}
