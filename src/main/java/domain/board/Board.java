package domain.board;

import domain.board.position.Position;
import domain.board.position.Vector;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public void move(final String source, final String target) {
        move(Position.from(String.valueOf(source.charAt(0)), String.valueOf(source.charAt(1))),
                Position.from(String.valueOf(target.charAt(0)), String.valueOf(target.charAt(1))));
    }

    public void move(final Position source, final Position target) {
        validateMovement(source, target);
        updateBoard(source, target);
    }

    public double calculateScore(final Color color) {
        final List<Piece> pieces = getPiecesBy(color);
        double totalSCore = calculateWithoutPawnScore(color, pieces);
        totalSCore += calculatePawnScore(color);
        return totalSCore;
    }

    private List<Piece> getPiecesBy(final Color color) {
        return squares.values().stream().filter(piece -> piece.hasColor(color)).toList();
    }

    private double calculateWithoutPawnScore(final Color color, final List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> piece.hasColor(color))
                .filter(piece -> !piece.isPawn())
                .map(Piece::getScore)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calculatePawnScore(final Color color) {
        final Map<Integer, Long> collect = squares.entrySet()
                .stream()
                .filter(entry -> entry.getValue().hasColor(color))
                .filter(entry -> entry.getValue().isPawn())
                .map(entry -> entry.getKey().toFileIndex())
                .collect(Collectors.groupingBy(fileIndex -> fileIndex, Collectors.counting()));

        return collect.values().stream().map(this::determinePawnScore).mapToDouble(Double::doubleValue).sum();
    }

    private double determinePawnScore(final Long counter) {
        if (counter >= 2) {
            return (double) counter / 2;
        }
        return counter;
    }

    private void validateMovement(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
        final Vector vector = new Vector(source, target);

        validateEmptiness(currentPiece);
        validateReachability(vector, currentPiece, targetPiece);
        validateNoPieceOnPath(source, vector);
    }

    private void validateEmptiness(final Piece currentPiece) {
        if (currentPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 말이 선택되지 않았습니다");
        }
    }

    private void validateNoPieceOnPath(final Position source, final Vector vector) {
        if (isPiecesPossiblyExistOnPath(vector)) {
            validateNoPieceExistInPath(vector, source);
        }
    }

    private boolean isPiecesPossiblyExistOnPath(final Vector vector) {
        return vector.hasAbsoluteValueMoreOrEqualThan(2) && vector.isStraightOrDiagonal();
    }

    private void validateReachability(final Vector vector, final Piece currentPiece, final Piece targetPiece) {
        if (!currentPiece.isReachable(vector, targetPiece)) {
            throw new IllegalArgumentException("해당 말로 해당 위치를 갈 수 없습니다");
        }
    }

    private void validateNoPieceExistInPath(final Vector vector, final Position source) {
        final List<Position> path = vector.generatePathExcludingEndpoints(source);
        final boolean isPieceExistInPath = !path.stream().map(squares::get).allMatch(Piece::isEmpty);

        if (isPieceExistInPath) {
            throw new IllegalArgumentException("이동 경로에 다른 말이 놓져 있습니다");
        }
    }


    private void updateBoard(final Position source, final Position target) {
        squares.put(target, squares.get(source).move());
        squares.put(source, Empty.INSTANCE);

    }

    public boolean isKingDeadOf(final Color color) {
        return squares.values()
                .stream()
                .filter(Piece::isKing)
                .map(Piece::getColor)
                .noneMatch(r -> r.isSameColor(color));
    }

    public Piece getPiece(final String source) {
        return squares.get(Position.from(String.valueOf(source.charAt(0)), String.valueOf(source.charAt(1))));
    }

    public Piece getPiece(final Position source) {
        return squares.get(source);
    }


    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
