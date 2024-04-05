package chess.controller;

import chess.controller.command.Command;
import chess.domain.game.Game;
import chess.service.DatabaseConnector;
import chess.service.GameDaoImpl;
import chess.service.GameService;
import chess.view.InputView;
import chess.view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameService gameService = new GameService(new GameDaoImpl(new DatabaseConnector()));
        outputView.printCommandGuide();

        Command command = inputView.readCommand();
        Game game = command.execute(gameService, outputView);

        while (command.isNotEnd() && game.isNotKingDead()) {
            command = inputView.readCommand();
            command.execute(game, gameService, outputView);
        }
    }
}
