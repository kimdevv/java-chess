package chess.fen;

import static chess.fen.PieceFenMapper.fenFrom;
import static chess.fen.PieceFenMapper.pieceOf;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

public class Fen {

    private static final String PATTERN = "^(?:[rnbqkpRNBQKP1-8]{1,8}/){7}[rnbqkpRNBQKP1-8]{1,8}$";

    private final String fen;

    public Fen(final String fen) {
        if (!fen.matches(PATTERN)) {
            throw new IllegalArgumentException("FEN 형식에 맞지 않습니다: %s".formatted(fen));
        }
        this.fen = fen;
    }

    public static Fen from(final Chessboard chessboard) {
        StringJoiner fen = new StringJoiner("/");
        for (final Rank rank : Rank.values()) {
            fen.add(rowToFen(chessboard, rank));
        }
        return new Fen(fen.toString());
    }

    private static StringBuilder rowToFen(final Chessboard chessboard, final Rank rank) {
        // TODO: 들여쓰기 1단계
        StringBuilder row = new StringBuilder();
        int emptySquareCount = 0;
        for (final File file : File.values()) {
            Position position = Position.of(file, rank);
            if (chessboard.isEmpty(position)) {
                emptySquareCount++;
                continue;
            }
            emptySquareCount = appendCount(emptySquareCount, row);
            row.append(fenFrom(chessboard.get(position)));
        }
        appendCount(emptySquareCount, row);
        return row;
    }

    private static int appendCount(final int emptySquareCount, final StringBuilder row) {
        if (emptySquareCount > 0) {
            row.append(emptySquareCount);
        }
        return 0;
    }

    public Set<Piece> toPieces() {
        Set<Piece> pieces = new HashSet<>();
        String[] piecesByFen = fen.split("/");
        for (int row = 0; row < piecesByFen.length; row++) {
            pieces.addAll(toPieceRow(piecesByFen[row], row));
        }
        return Collections.unmodifiableSet(pieces);
    }

    private Set<Piece> toPieceRow(final String rowByFen, final int row) {
        Set<Piece> pieces = new HashSet<>();
        int column = 0;
        for (final char squareByFen : rowByFen.toCharArray()) {
            pieceOf(squareByFen, row, column).ifPresent(pieces::add);
            column += getIfDigit(squareByFen);
        }
        return pieces;
    }

    private int getIfDigit(final char fen) {
        if (Character.isDigit(fen)) {
            return Character.getNumericValue(fen);
        }
        return 1;
    }

    public String value() {
        return fen;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Fen other
                && fen.equals(other.fen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fen);
    }
}
