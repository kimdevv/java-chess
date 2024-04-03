package domain.board;

import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoard {
    private static final int SAME_FILE_PAWN_COUNT = 2;
    private static final double SAME_FILE_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> board;
    private Color turn;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
        this.turn = Color.WHITE;
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = findByPosition(source);
        sourcePiece.validateMovement(source, target, findByPosition(target).color());
        validatePathClear(source, target);
        changeOppositeTurn(sourcePiece);
        moveToTargetPosition(source, target);
    }

    private void changeOppositeTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException("자신의 말을 움직여야 합니다.");
        }
        turn = turn.opposite();
    }

    private Piece findByPosition(Position position) {
        return board.getOrDefault(position, Empty.create());
    }

    private void validatePathClear(Position source, Position target) {
        List<Position> path = source.findPathTo(target);
        boolean existPiece = path.stream().anyMatch(board::containsKey);
        if (existPiece) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
    }

    private void moveToTargetPosition(Position source, Position target) {
        board.put(target, board.remove(source));
    }

    public Map<Position, Piece> getPositionAndPieces() {
        return Collections.unmodifiableMap(board);
    }

    public double calculateScore(Color color) {
        return calculatePieceScore(color) - calculateSameFilePawnScore(color);
    }

    private double calculatePieceScore(Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculateSameFilePawnScore(Color color) {
        Map<File, Long> sameFilePawnCount = board.entrySet().stream()
                .filter(entry -> isSameColorPawn(entry, color))
                .map(entry -> entry.getKey().file())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return sameFilePawnCount.values().stream()
                .filter(count -> count >= SAME_FILE_PAWN_COUNT)
                .mapToDouble(count -> count * SAME_FILE_PAWN_SCORE)
                .sum();
    }

    private boolean isSameColorPawn(Entry<Position, Piece> entry, Color color) {
        return entry.getValue().isSameColor(color) && entry.getValue().isSameType(Type.PAWN);
    }

    public boolean isKingCaptured() {
        return isKingCaptured(Color.BLACK) || isKingCaptured(Color.WHITE);
    }

    private boolean isKingCaptured(Color color) {
        return board.values().stream()
                .filter(piece -> piece.isSameType(Type.KING))
                .noneMatch(piece -> piece.isSameColor(color));
    }

    public Winner getWinner() {
        if (isKingCaptured(Color.BLACK)) {
            return Winner.WHITE;
        }
        if (isKingCaptured(Color.WHITE)) {
            return Winner.BLACK;
        }
        return Winner.DRAW;
    }
}
