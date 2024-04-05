package chess.controller.command;

import chess.domain.game.Game;
import chess.domain.game.InitialGameCreator;
import chess.service.GameService;
import chess.view.OutputView;

public class Start implements Command {

    @Override
    public Game execute(GameService gameService, OutputView outputView) {
        Game game = new Game(new InitialGameCreator());
        outputView.printBoard(game.board());
        return game;
    }

    @Override
    public void execute(Game game, GameService gameService, OutputView outputView) {
        throw new IllegalArgumentException("사용할 수 없는 명령어입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
