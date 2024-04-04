package domain;

import domain.board.ChessBoard;
import domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public ChessGame(ChessBoard chessBoard, Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public void turnCheck(Position current) {
        turn.check(chessBoard, current);
    }

    public void movePiece(Position current, Position target) {
        chessBoard.move(current, target);
        turn.end();
    }

    public boolean isAllKingAlive() {
        return chessBoard.isAllKingAlive();
    }

    public Turn turn() {
        return turn;
    }

    public ChessBoard chessBoard() {
        return chessBoard;
    }
}
