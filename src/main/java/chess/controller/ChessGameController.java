package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.gamecommand.EndState;
import chess.domain.chessgame.gamecommand.GameCommandState;
import chess.domain.chessgame.gamecommand.GameElements;
import chess.domain.chesspiece.Camp;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessGameController {

    private final ChessGameService chessGameService = new ChessGameService();

    public void run() {
        OutputView.printStartMessage();
        gameStart();
    }

    private void gameStart() {
        try {
            chessGameService.initialSetting();
            ChessBoard chessBoard = chessGameService.chessBoardSetting();
            executeGameCommand(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            gameStart();
        }
    }

    private void executeGameCommand(ChessBoard chessBoard) {
        ChessGame chessGame = new ChessGame(chessBoard);
        GameElements gameElements = requestGameCommand();
        GameCommandState gameCommandState = gameElements.findGameCommandState();
        while (!isEndState(gameElements)) {
            chessGame.executeGame(gameCommandState, gameElements);
            updateGame(chessBoard, gameElements);
            OutputView.printGameExecute(gameCommandState, chessBoard);
            if (chessGame.isGameFinished()) {
                handleKingDead(chessGame);
                chessGameService.resetChessGame();
                break;
            }
            gameElements = requestGameCommand();
            gameCommandState = gameElements.findGameCommandState();
        }
    }

    private GameElements requestGameCommand() {
        return repeatUntilSuccess(InputView::requestGameCommand);
    }

    private boolean isEndState(GameElements gameElements) {
        GameCommandState gameCommandState = gameElements.findGameCommandState();
        return EndState.getInstance().isEndState(gameCommandState);
    }

    private void handleKingDead(ChessGame chessGame) {
        Camp campKingAlive = chessGame.findCampKingAlive();
        OutputView.printWhenKingDead(campKingAlive);
    }

    private void updateGame(ChessBoard chessBoard, GameElements gameElements) {
        List<Square> moveSquares = gameElements.createMoveSquare();
        chessGameService.updateChessGame(chessBoard, moveSquares);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(supplier);
        }
    }
}
