package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(final BoardInitializer boardInitializer) {
        this.board = boardInitializer.initialize();
    }

    public boolean tryMove(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        if (sourcePiece.canMove(source, target, board)) {
            move(source, target);
            return true;
        }

        return false;
    }

    private void move(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, Empty.getInstance());
    }

    public boolean isKingKilled() {
        int kingCount = (int) board.entrySet().stream()
                .filter(entry -> entry.getValue().getPieceType() == PieceType.KING)
                .count();
        return kingCount < 2;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Piece get(final Position source) {
        return board.get(source);
    }
}
