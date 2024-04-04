package command;

import domain.ChessGame;
import domain.status.GameStatus;
import view.OutputView;

public class Status implements Command {

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        GameStatus gameStatus = GameStatus.from(chessGame.chessBoard());
        outputView.printStatus(gameStatus);
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
