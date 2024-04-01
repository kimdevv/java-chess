package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Board {
    private static final int DEFAULT_KINGS_COUNT = 2;

    private final Map<Position, Piece> pieces;
    private final ScoreCalculator scoreCalculator;

    public Board(Map<Position, Piece> pieces, ScoreCalculator scoreCalculator) {
        this.pieces = pieces;
        this.scoreCalculator = scoreCalculator;
    }

    public Piece findPieceByPosition(Position position) {
        return pieces.getOrDefault(position, new Empty());
    }

    public void move(Position sourcePosition, Position targetPosition, Color color) {
        Piece sourcePiece = findPieceByPosition(sourcePosition);

        validate(sourcePosition, targetPosition, color, sourcePiece);

        pieces.put(targetPosition, sourcePiece);
        pieces.remove(sourcePosition);
    }

    private void validate(Position sourcePosition, Position targetPosition, Color color, Piece sourcePiece) {
        validatePositionIsSame(sourcePosition, targetPosition);
        validateSourceIsEmpty(sourcePiece);
        validateIsNotMyTurn(color, sourcePiece);
        validateIsMovablePosition(sourcePosition, targetPosition, sourcePiece);
    }

    private void validatePositionIsSame(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("같은 위치를 선택할 수 없습니다.");
        }
    }

    private void validateSourceIsEmpty(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("선택한 기물이 존재하지 않습니다.");
        }
    }

    private void validateIsNotMyTurn(Color color, Piece sourcePiece) {
        if (!sourcePiece.isSameColor(color)) {
            throw new IllegalArgumentException("선택한 위치의 기물은 내 말이 아닙니다.");
        }
    }

    private void validateIsMovablePosition(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        Set<Position> movablePositions = sourcePiece.calculateMovablePositions(sourcePosition, this);
        if (!movablePositions.contains(targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    public ChessGameResult calculateGameScore() {
        return scoreCalculator.calculate(pieces);
    }

    public boolean isKingDead() {
        return calculateKingsCount() != DEFAULT_KINGS_COUNT;
    }

    private long calculateKingsCount() {
        return pieces.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
