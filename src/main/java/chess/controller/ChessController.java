package chess.controller;

import static chess.exception.RetryHandler.retryOnException;

import chess.domain.Scores;
import chess.dto.ChessBoardDto;
import chess.dto.CommandDto;
import chess.domain.board.ChessBoard;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessService chessService;

    public ChessController(final InputView inputView, final OutputView outputView, final ChessService chessService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessService = chessService;
    }

    public void run() {
        outputView.printCommandMenu();
        retryOnException(this::startGame);
    }

    private void startGame() {
        final CommandDto commandDto = CommandDto.fromStart(inputView.readCommand());

        if (commandDto.isReload()) {
            startGame(chessService.findRecentBoard());
        }

        if (commandDto.isStart()) {
            startGame(chessService.createBoard());
        }
    }

    private void startGame(final ChessBoard chessBoard) {
        outputView.printChessBoard(ChessBoardDto.from(chessBoard.getPieces()));
        retryOnException(() -> playTurn(chessBoard));
    }

    private void playTurn(final ChessBoard chessBoard) {
        while (true) {
            final CommandDto commandDto = CommandDto.fromPlay(inputView.readCommand());

            if (commandDto.isMove()) {
                chessService.move(commandDto, chessBoard);
            }

            printResult(commandDto, chessBoard);

            if (commandDto.isEnd() || chessBoard.isKingCaught()) {
                break;
            }
        }
    }

    private void printResult(final CommandDto commandDto, final ChessBoard chessBoard) {
        if (commandDto.isMove()) {
            outputView.printChessBoard(ChessBoardDto.from(chessBoard.getPieces()));
        }
        if (commandDto.isStatus()) {
            outputView.printScores(Scores.from(chessBoard));
        }
        if (chessBoard.isKingCaught()) {
            outputView.printWinner(chessBoard.getTurn());
        }
    }
}
