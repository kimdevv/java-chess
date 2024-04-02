package chess;

import chess.controller.ChessGameController;
import chess.view.OutputView;

public class Application {
    private static final long CHESS_ROOM_ID = 1;

    public static void main(String[] args) {
        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        ChessGameController chessGameController = new ChessGameController(CHESS_ROOM_ID);

        chessGameController.startGame();
    }
}
