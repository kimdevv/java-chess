package chess.domain.chessboard;

import java.util.List;
import java.util.Map;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

public class Chessboard {

    private static final int CHECKMATE_KING_COUNT = 1;

    private final Map<Position, Piece> chessboard;

    protected Chessboard(final Map<Position, Piece> chessboard) {
        this.chessboard = chessboard;
    }

    public static boolean isInBoard(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public Piece move(final Position source, final Position target) {
        Piece piece = get(source).move(this, target);
        remove(source);
        put(target, piece);
        return piece;
    }

    public Piece get(final Position position) {
        validateSource(position);
        return chessboard.get(position);
    }

    private void validateSource(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다: %s".formatted(position));
        }
    }

    public boolean isEmpty(final Position position) {
        return !isPresent(position);
    }

    public boolean isPresent(final Position position) {
        return chessboard.containsKey(position);
    }

    private void remove(final Position position) {
        chessboard.remove(position);
    }

    private void put(final Position position, final Piece piece) {
        chessboard.put(position, piece);
    }

    public List<Piece> getAllFrom(final File file) {
        return Positions.from(file)
                .stream()
                .filter(this::isPresent)
                .map(chessboard::get)
                .toList();
    }

    public boolean isCheckmate() {
        return chessboard.values()
                .stream()
                .filter(King.class::isInstance)
                .count() == CHECKMATE_KING_COUNT;
    }

    public Color winner() {
        if (!isCheckmate()) {
            throw new IllegalStateException("체크메이트인 경우에만 승자를 알 수 있습니다.");
        }
        return chessboard.values()
                .stream()
                .filter(King.class::isInstance)
                .findFirst()
                .map(Piece::color)
                .orElseThrow(IllegalStateException::new);
    }
}
