package command;

import domain.ChessGame;
import view.OutputView;

public interface Command {

    void execute(ChessGame chessGame, OutputView outputView);

    boolean isNotEnd();

    boolean isEnd();
}
