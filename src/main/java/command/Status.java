package command;

import command.base.Command;
import java.util.List;
import service.ChessGameService;
import service.dto.GameScoreDto;
import state.chessGame.base.ChessGameState;
import view.OutputView;

public class Status implements Command {

    private final ChessGameService chessGameService;

    public Status(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState) {
        GameScoreDto gameScoreDto = chessGameService.calculateScore(chessGameState);
        OutputView.printStatus(gameScoreDto);
        return chessGameState;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) {
        throw new IllegalArgumentException("잘못된 입력 값을 받았습니다.");
    }
}
