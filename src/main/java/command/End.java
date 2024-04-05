package command;

import command.base.Command;
import java.util.List;
import state.chessGame.base.ChessGameState;

public class End implements Command {

    public End() {
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState) {
        return chessGameState.end();
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) {
        throw new IllegalArgumentException("잘못된 입력 값을 받았습니다.");
    }
}
