package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Obstacle;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Board move(final Position source, final Position target, final Color color) {
        final Piece piece = board.get(source);
        validateEmpty(source);
        validateColorTurn(piece, color);
        validateMovement(source, target);

        board.put(target, piece);
        board.put(source, Piece.EMPTY_PIECE);

        return new Board(board);
    }

    private void validateEmpty(final Position source) {
        if (board.get(source).isEmpty()) {
            throw new IllegalArgumentException("기물이 존재하지 않아 이동시킬 수 없습니다.");
        }
    }

    private void validateColorTurn(final Piece piece, final Color color) {
        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException("상대 팀의 기물을 이동시킬 수 없습니다.");
        }
    }

    private void validateMovement(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);
        validateSameColorPiece(sourcePiece, targetPiece);
        boolean canAttack = sourcePiece.canAttack(source, target, new Obstacle(getNonEmptyPiecePosition()));
        boolean canMove = sourcePiece.canMove(source, target, new Obstacle(getNonEmptyPiecePosition()));
        if (!canAttack && !canMove) {
            throw new IllegalArgumentException("해당 말로 이동할 수 없는 위치입니다.");
        }
    }

    private void validateSameColorPiece(final Piece sourcePiece, final Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece.getColor())) {
            throw new IllegalArgumentException("같은 색상의 말의 위치로 이동시킬 수 없습니다.");
        }
    }

    public boolean isKingAlone() {
        return isKingNotExist(Color.WHITE) || isKingNotExist(Color.BLACK);
    }

    private boolean isKingNotExist(Color color) {
        return !board.containsValue(Piece.of(PieceType.KING, color));
    }

    private List<Position> getNonEmptyPiecePosition() {
        return board.entrySet().stream()
                .filter(position -> !position.getValue().isEmpty())
                .map(Entry::getKey)
                .toList();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Color getAloneKingColor() {
        if (isKingAlone()) {
            return findKingColor();
        }
        throw new IllegalArgumentException("두 킹이 존재하고 있습니다.");
    }

    private Color findKingColor() {
        return board.values().stream()
                .filter(piece -> piece.getPieceType() == PieceType.KING)
                .map(Piece::getColor)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("두 킹이 존재하지 않습니다."));
    }
}
