package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    public static void main(String[] args) {
        ChessConsoleManager chessConsoleManager = new ChessConsoleManager(new InputView(), new OutputView());
        chessConsoleManager.runChess();
    }
}
