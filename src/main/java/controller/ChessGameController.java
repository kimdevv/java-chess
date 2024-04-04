package controller;

import controller.dto.ChessBoardStatusDto;
import controller.dto.ScoreDto;
import domain.game.ChessGame;
import domain.piece.Color;
import service.ChessGameService;
import view.InputView;
import view.OutputView;
import view.dto.CommandRequest;
import view.util.Command;

public class ChessGameController {
    private static final ChessGameService chessGameService = ChessGameService.getInstance();
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();

        ChessGame chessGame = initializeGame();
        printCurrentBoardStatus(chessGame);
        start(chessGame);
    }

    private ChessGame initializeGame() {
        Command command = inputView.receiveInitialCommand();

        if (Command.START == command) {
            return chessGameService.createInitialGame();
        }
        return chessGameService.loadGame();
    }

    private void start(ChessGame chessGame) {
        boolean isPlaying;

        do {
            isPlaying = playGame(chessGame);
        } while (isPlaying);
    }

    private boolean playGame(ChessGame chessGame) {
        outputView.printCurrentTurnColor(chessGame.getMovablePieceColor().name());
        CommandRequest commandRequest = inputView.receivePlayingCommand();
        Command command = commandRequest.getCommand();

        if (command == Command.MOVE) {
            return move(chessGame, commandRequest);
        }
        if (command == Command.STATUS) {
            printScore(chessGame, false);
            return true;
        }
        return isCommandSaveOrEnd(command, chessGame);
    }

    private boolean isCommandSaveOrEnd(Command command, ChessGame chessGame) {
        if (command == Command.END) {
            printScore(chessGame, true);
            chessGameService.deleteGame();
        }
        if (command == Command.SAVE) {
            printScore(chessGame, false);
        }
        return false;
    }

    private boolean move(ChessGame chessGame, CommandRequest commandRequest) {
        boolean isKingRemoved = chessGameService.move(
                chessGame, commandRequest.getStartCoordinate(), commandRequest.getDestinationCoordinate()
        );
        printCurrentBoardStatus(chessGame);

        return isNextTurnAvailable(isKingRemoved, chessGame);
    }

    private boolean isNextTurnAvailable(boolean isKingRemoved, ChessGame chessGame) {
        if (isKingRemoved) {
            Color removedKingColor = chessGame.getMovablePieceColor();
            outputView.printMessageWhenRemoveOpponentKing(
                    removedKingColor.name(), removedKingColor.getOpponentColor().name()
            );
        }
        return !isKingRemoved;
    }

    private void printCurrentBoardStatus(ChessGame chessGame) {
        outputView.printBoard(ChessBoardStatusDto.from(chessGameService.getPiecesInBoard(chessGame)));
    }

    private void printScore(ChessGame chessGame, boolean shouldPrintResult) {
        Color playerColor = chessGame.getMovablePieceColor();
        ScoreDto player = ScoreDto.from(playerColor, chessGame.calculatePlayerScore());
        ScoreDto opponent = ScoreDto.from(playerColor.getOpponentColor(), chessGame.calculateOpponentScore());

        if (shouldPrintResult) {
            outputView.printScoreResult(player, opponent);
            return;
        }
        outputView.printScore(player, opponent);
    }
}
