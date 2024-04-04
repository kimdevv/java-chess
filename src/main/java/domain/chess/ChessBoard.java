package domain.chess;

import domain.chess.piece.Piece;
import domain.chess.piece.Pieces;
import domain.chess.piece.Color;
import domain.chess.piece.Point;
import domain.chess.factory.ChessBoardGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ChessBoard {
    private final Pieces pieces;
    private final Color turn;
    private final int gameId;

    public ChessBoard(final Pieces pieces, final int gameId) {
        this(pieces, Color.WHITE, gameId);
    }

    public ChessBoard(final Pieces pieces, final Color color, final int gameId) {
        validatePieces(pieces);
        this.pieces = pieces;
        this.turn = color;
        this.gameId = gameId;
    }

    private void validatePieces(final Pieces pieces) {
        final var kingCount = pieces.getKingCount();

        if (kingCount != Color.values().length) {
            throw new IllegalStateException(String.format("왕이 총 %d 명입니다", kingCount));
        }
    }

    public Optional<Piece> findPiece(final Point point) {
        return this.pieces.findPieceWithPoint(point);
    }

    public Piece move(final Point startPoint, final Point endPoint) {
        validatePoint(startPoint, endPoint);
        final Optional<Piece> optionalPiece = this.pieces.findPieceWithPoint(startPoint);
        final Piece piece = optionalPiece.get();
        pieces.move(piece, endPoint);
        return piece;
    }

    private void validatePoint(final Point startPoint, final Point endPoint) {
        if (startPoint.equals(endPoint)) {
            throw new IllegalArgumentException("같은 위치로 이동할 수 없습니다.");
        }
        validatePiece(startPoint);
    }

    private void validatePiece(final Point startPoint) {
        final Optional<Piece> optionalPiece = this.pieces.findPieceWithPoint(startPoint);
        if (optionalPiece.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다", startPoint));
        }
        optionalPiece.ifPresent(this::validateDifferentColor);
    }

    private void validateDifferentColor(final Piece piece) {
        if (piece.isEqualColor(turn)) {
            return;
        }
        throw new IllegalArgumentException(String.format("현재는 %s의 차례입니다.", turn));
    }

    public static ChessBoard createDefaultBoard(final int gameId) {
        return ChessBoardGenerator.createDefaultBoard(gameId);
    }

    public Color getTurn() {
        return turn;
    }

    public double getTurnScore() {
        return pieces.getScore(turn);
    }

    public List<Piece> getPieces() {
        return this.pieces.allPieces();
    }

    public int getGameId() {
        return gameId;
    }
}
