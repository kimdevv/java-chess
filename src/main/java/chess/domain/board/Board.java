package chess.domain.board;

import chess.domain.game.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(Position position) {
        return pieces.getOrDefault(position, new Empty());
    }

    public void move(Position sourcePosition, Position targetPosition, Color color) {
        Piece sourcePiece = findPieceByPosition(sourcePosition);

        validateMove(sourcePosition, targetPosition, color, sourcePiece);

        pieces.put(targetPosition, sourcePiece);
        pieces.remove(sourcePosition);
    }

    public double calculateScore(Color color) {
        Map<Position, Piece> colorPieces = getPiecesByColor(color);

        return ScoreCalculator.calculateScore(colorPieces);
    }

    private Map<Position, Piece> getPiecesByColor(Color color) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void validateMove(Position sourcePosition, Position targetPosition, Color color, Piece sourcePiece) {
        validateIsSamePosition(sourcePosition, targetPosition);
        validateSourceIsEmpty(sourcePiece);
        validateIsNotMyColor(color, sourcePiece);
        validateIsNotMovablePosition(sourcePosition, targetPosition, sourcePiece);
    }

    private void validateIsSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("같은 위치를 선택할 수 없습니다.");
        }
    }

    private void validateSourceIsEmpty(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("선택한 기물이 존재하지 않습니다.");
        }
    }

    private void validateIsNotMyColor(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameColor(color)) {
            throw new IllegalArgumentException("선택한 위치의 기물은 내 말이 아닙니다.");
        }
    }

    private void validateIsNotMovablePosition(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        Set<Position> movablePositions = sourcePiece.calculateMovablePositions(sourcePosition, this);
        if (!movablePositions.contains(targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    public boolean isKingDead(Color color) {
        return pieces.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .noneMatch(Piece::isKing);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
