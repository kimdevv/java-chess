package chess.controller.command;

import chess.service.ChessGameService;
import chess.service.dto.ScoreStatusDto;
import chess.view.OutputView;

public class StatusCommand implements Command {

    @Override
    public ExecuteResult execute(ChessGameService chessGameService, OutputView outputView) {
        ScoreStatusDto scoreStatusDto = chessGameService.calculateScoreStatus();
        outputView.printStatusMessage(scoreStatusDto);
        return new ExecuteResult(true, true);
    }
}
