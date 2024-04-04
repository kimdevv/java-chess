package command;

import domain.ChessGame;
import view.OutputView;

public class End implements Command {

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
