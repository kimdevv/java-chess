package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import observable.Observable;

public class ChessGame implements Observable<Piece> {

    private final BoardAdaptor board;
    private Color activeColor;
    boolean isKingDead;

    public ChessGame(final BoardAdaptor board) {
        this.board = board;
        this.activeColor = Color.WHITE;
        isKingDead = false;
        board.subscribe(this);
    }


    public void move(final String source, final String target) {
        if (isKingDead) {
            throw new IllegalCallerException("진행 중인 게임이 아닙니다.");
        }
        validateCurrentColor(board.getPiece(source));
        board.move(source, target);
    }

    private void validateCurrentColor(final Piece currentPiece) {
        if (!currentPiece.hasColor(activeColor)) {
            throw new IllegalArgumentException(
                    String.format("현재 차례: %s, 현재 차례의 말만 움직일 수 있습니다", activeColor.name()));
        }
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public Color getColor() {
        return this.activeColor;
    }

    @Override
    public void update(final Piece piece) {
        if (piece.isKing()) {
            isKingDead = true;
            return;
        }
        activeColor = activeColor.reverse();
    }

    public Piece getPiece(final String target) {
        return board.getPiece(target);
    }

    public Double calculateScore(final Color color) {
        return board.calculateScore(color);
    }

    public boolean isKingDeadOf(final Color color) {
        return board.isKingDeadOf(color);
    }
}
