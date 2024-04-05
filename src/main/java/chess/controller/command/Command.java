package chess.controller.command;

import chess.domain.game.Game;
import chess.service.GameService;
import chess.view.OutputView;

public interface Command {

    Game execute(GameService gameService, OutputView outputView);

    void execute(Game game, GameService gameService, OutputView outputView);

    boolean isNotEnd();
}
