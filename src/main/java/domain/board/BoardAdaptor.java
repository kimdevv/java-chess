package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import observable.Observable;
import observable.Publishable;

public class BoardAdaptor implements Publishable<Piece> {
    private final Board board;
    private Observable<Piece> observable;

    public BoardAdaptor(final Board board) {
        this.board = board;
    }

    public void move(final String source, final String target) {
        push(getPiece(target));
        board.move(source, target);
    }

    @Override
    public void subscribe(final Observable<Piece> observable) {
        this.observable = observable;
    }

    @Override
    public void push(final Piece piece) {
        observable.update(piece);
    }

    public Piece getPiece(final String source) {
        return board.getPiece(source);
    }

    public Double calculateScore(final Color color) {
        return board.calculateScore(color);
    }

    public boolean isKingDeadOf(final Color color) {
        return board.isKingDeadOf(color);
    }
}
