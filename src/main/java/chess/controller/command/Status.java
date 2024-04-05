package chess.controller.command;

import chess.domain.game.Game;
import chess.domain.game.Score;
import chess.service.GameService;
import chess.view.OutputView;

public class Status implements Command {

    @Override
    public Game execute(GameService gameService, OutputView outputView) {
        throw new IllegalArgumentException("사용할 수 없는 명령어입니다.");
    }

    @Override
    public void execute(Game game, GameService gameService, OutputView outputView) {
        Score score = game.calculateScore();
        outputView.printScore(score);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
