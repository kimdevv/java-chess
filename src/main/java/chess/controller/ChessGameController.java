package chess.controller;

import chess.domain.board.ClearBoardInitializer;
import chess.domain.board.SavedBoardInitializer;
import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.scorerule.Referee;
import chess.service.ChessGameService;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ChessGameController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService gameService;

    public ChessGameController(final InputView inputView, final OutputView outputView,
                               final ChessGameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void start() {
        outputView.printInitialMessage();
        checkStart(inputView.getGameCommand());

        if (gameService.existSavedGame() && inputView.isPlaySavedGame()) {
            playSavedGame();
            return;
        }

        playNewGame();
    }

    private void checkStart(final GameCommand gameCommand) {
        if (gameCommand.isNotStartCommand()) {
            throw new IllegalArgumentException("시작 명령어를 입력해주세요.");
        }
    }

    private void playSavedGame() {
        Color turnColor = gameService.findTurn()
                .orElseThrow(() -> new IllegalStateException("저장된 게임이 정상적이지 않습니다. 다시 실행해주세요."));
        Game game = Game.of(new SavedBoardInitializer(gameService.findAllPiece()), turnColor);
        outputView.printBoard(game.getBoard());
        outputView.printCurrentTurn(game.getTurn());
        execute(game);
    }

    private void playNewGame() {
        Game game = new Game(new ClearBoardInitializer());
        outputView.printBoard(game.getBoard());
        execute(game);
    }

    private void execute(final Game game) {
        GameCommand gameCommand = inputView.getGameCommand();

        while (isGameActionIfOngoing(game, gameCommand)) {
            executeMoveCommand(game, gameCommand);
            executeStatusCommand(game, gameCommand);
            gameCommand = inputView.getGameCommand();
        }

        checkGameRestart(gameCommand);
        checkGameFinish(game);
        checkGameTerminate(game, gameCommand);
    }

    private boolean isGameActionIfOngoing(final Game game, final GameCommand gameCommand) {
        return !game.isFinish() && gameCommand.isOnGoingGameCommand();
    }

    private void executeMoveCommand(final Game game, final GameCommand gameCommand) {
        if (gameCommand.isMoveCommand()) {
            executeTurn(game);
        }
    }

    private void executeStatusCommand(final Game game, final GameCommand gameCommand) {
        if (gameCommand.isViewStatusCommand()) {
            viewScore(game.getBoard());
        }
    }

    private void checkGameRestart(final GameCommand gameCommand) {
        if (gameCommand.isStartGameCommand()) {
            playNewGame();
        }
    }

    private void checkGameFinish(final Game game) {
        if (game.isFinish()) {
            outputView.printFinish();
            gameService.delete();
        }
    }

    private void checkGameTerminate(final Game game, final GameCommand gameCommand) {
        if (gameCommand.isEndCommand()) {
            gameService.saveGame(game);
        }
    }

    private void executeTurn(final Game game) {
        Position source = Position.of(inputView.getPosition());
        Position target = Position.of(inputView.getPosition());

        game.move(source, target);
        outputView.printBoard(game.getBoard());
    }

    private void viewScore(final Map<Position, Piece> board) {
        Referee referee = new Referee(board);

        double blackTeamScore = referee.calculateScore(Color.BLACK);
        outputView.printScore(blackTeamScore, Color.BLACK);
        double whiteTeamScore = referee.calculateScore(Color.WHITE);
        outputView.printScore(whiteTeamScore, Color.WHITE);
    }
}
