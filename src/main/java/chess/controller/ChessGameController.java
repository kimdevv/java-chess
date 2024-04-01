package chess.controller;

import chess.domain.ChessGame;
import chess.domain.ChessGameResult;
import chess.domain.ChessGameService;
import chess.domain.GameStatus;
import chess.domain.command.CommandCondition;
import chess.domain.command.CommandExecutor;
import chess.domain.command.GameCommand;
import chess.domain.position.Position;
import chess.dto.Move;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private final Map<GameCommand, CommandExecutor> commands;
    private final ChessGameService chessGameService;
    private final ChessGame chessGame;


    public ChessGameController(ChessGameService chessGameService, ChessGame chessGame) {
        this.commands = Map.of(
                GameCommand.START, args -> start(),
                GameCommand.MOVE, this::move,
                GameCommand.END, args -> end(),
                GameCommand.STATUS, args -> status()
        );
        this.chessGameService = chessGameService;
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printGameStartMessage();

        while (chessGame.isPlaying()) {
            repeatUntilValidCommand();
        }
    }

    private void repeatUntilValidCommand() {
        try {
            executeCommand();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatUntilValidCommand();
        }
    }

    private void executeCommand() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);
        CommandExecutor commandExecutor = commands.get(gameCommand);
        commandExecutor.execute(new CommandCondition(inputCommand));
    }

    private void start() {
        chessGame.start();
        loadRecentGameIfPresent();
        OutputView.printChessBoard(chessGame.getBoard());
    }

    private void loadRecentGameIfPresent() {
        List<Move> moves = chessGameService.getRecentPlayableGameMoves();
        moves.forEach(move -> chessGame.moveForHistory(move.source(), move.target()));
    }

    private void move(CommandCondition commandCondition) {
        Position source = Position.from(commandCondition.getSource());
        Position target = Position.from(commandCondition.getTarget());
        chessGame.move(source, target);
        if (chessGame.isKingDead()) {
            end();
            chessGameService.updateGameFinished();
        }
        OutputView.printChessBoard(chessGame.getBoard());

    }

    private void end() {
        chessGame.end();
        List<Move> moveHistory = chessGame.getMoveHistory();
        saveGameMoves(moveHistory);
    }

    private void saveGameMoves(List<Move> moveHistory) {
        GameStatus gameStatus = chessGame.getGameStatusByKing();
        Long chessGameId = saveGame(gameStatus);
        chessGameService.saveMoveHistory(moveHistory, chessGameId);
    }

    private Long saveGame(GameStatus gameStatus) {
        return chessGameService.save(gameStatus);
    }

    private void status() {
        ChessGameResult chessGameResult = chessGame.status();
        OutputView.printChessGameScore(chessGameResult);
    }
}
