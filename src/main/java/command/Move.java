package command;

import domain.ChessGame;
import domain.position.Position;
import view.OutputView;

public class Move implements Command {

    private final Position current;
    private final Position target;

    public Move(Position current, Position target) {
        this.current = current;
        this.target = target;
    }

    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.turnCheck(current);
        chessGame.movePiece(current, target);

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
