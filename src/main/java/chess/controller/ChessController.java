package chess.controller;

import chess.controller.command.CommandCondition;
import chess.controller.command.CommandExecutor;
import chess.controller.command.GameCommand;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameStatus;
import chess.domain.position.Move;
import chess.domain.position.Position;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {
    private final ChessGameService chessGameService;
    private final Map<GameCommand, CommandExecutor> commands = Map.of(
            GameCommand.START, (chessGame, commandCondition) -> start(chessGame),
            GameCommand.STATUS, (chessGame, commandCondition) -> status(chessGame),
            GameCommand.END, (chessGame, commandCondition) -> end(chessGame),
            GameCommand.MOVE, this::move
    );

    public ChessController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        OutputView.printGameStartMessage();
        ChessGame chessGame = chessGameService.createInitialChessGame();

        while (chessGame.isPlaying()) {
            repeatUntilValidCommand(chessGame);
        }

        whenKingIsDead(chessGame);
    }

    private void executeCommand(ChessGame chessGame) {
        List<String> inputCommand = InputView.readGameCommand();
        CommandCondition commandCondition = CommandCondition.of(inputCommand);
        GameCommand gameCommand = commandCondition.gameCommand();

        commands.get(gameCommand).execute(chessGame, commandCondition);
    }

    private void repeatUntilValidCommand(ChessGame chessGame) {
        try {
            executeCommand(chessGame);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatUntilValidCommand(chessGame);
        }
    }

    private void whenKingIsDead(ChessGame chessGame) {
        if (!chessGame.isKingDead()) {
            return;
        }

        OutputView.printGameResult(chessGame.gameResult());
        chessGameService.deleteAllMoves();
    }

    private void start(ChessGame chessGame) {
        List<Move> moveHistories = chessGameService.getMoveHistories();
        chessGame.start(moveHistories);

        printChessBoard(chessGame);
    }

    private void move(ChessGame chessGame, CommandCondition commandCondition) {
        Position source = Position.convert(commandCondition.getSource());
        Position target = Position.convert(commandCondition.getTarget());

        chessGame.move(source, target);
        chessGameService.addMoveHistory(source, target);

        printChessBoard(chessGame);
    }

    private void end(ChessGame chessGame) {
        chessGame.end();
    }

    private void status(ChessGame chessGame) {
        ChessGameStatus chessGameStatus = chessGame.status();

        OutputView.printChessGameStatus(chessGameStatus);
    }

    private void printChessBoard(ChessGame chessGame) {
        OutputView.printChessBoard(chessGame.board(), chessGame.currentColor());
    }
}
