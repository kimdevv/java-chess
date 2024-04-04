package command;

import domain.ChessGame;
import view.OutputView;

public class Start implements Command {

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        outputView.printChessBoard(chessGame.chessBoard());
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
