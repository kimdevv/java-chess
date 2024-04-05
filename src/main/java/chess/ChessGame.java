package chess;

import static chess.exception.ExceptionHandler.retry;

import java.util.Optional;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.chessboard.ChessboardRepository;
import chess.domain.score.Scoreboard;
import chess.fen.dao.FenDao;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessResultDto;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    // TODO: DAO 로직 분리

    private final InputView inputView;
    private final ResultView resultView;
    private final ChessboardRepository repository;

    public ChessGame(final InputView inputView, final ResultView resultView, final ChessboardRepository repository) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.repository = repository;
    }

    public void run() {
        resultView.printGameStartMessage();
        Command command = inputView.askStartOrEnd();
        if (command.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = initializeChessboard();
        resultView.printBoard(new ChessboardDto(chessboard));
        retry(() -> playByCommand(chessboard, inputView.askMoveOrEnd()));
        printResultIfCheckmate(chessboard);
    }

    private Chessboard initializeChessboard() {
        Optional<Chessboard> chessboard = repository.find();
        if (chessboard.isEmpty()) {
            return repository.save(ChessboardFactory.create());
        }
        Command command = inputView.askLoadOrStart();
        if (command.isLoad()) {
            return chessboard.get();
        }
        return repository.save(ChessboardFactory.create());
    }

    private void playByCommand(final Chessboard chessboard, final Command command) {
        if (!command.isMove()) {
            return;
        }
        MoveCommand moveCommand = command.toMoveCommand();
        chessboard.move(moveCommand.getSource(), moveCommand.getTarget());
        repository.update(chessboard);
        if (chessboard.isCheckmate()) {
            return;
        }
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard, inputView.askMoveOrEnd());
    }

    private void printResultIfCheckmate(final Chessboard chessboard) {
        if (!chessboard.isCheckmate()) {
            return;
        }
        FenDao fenDao = new FenDao();
        fenDao.delete();
        resultView.printCheckmate();
        Command command = inputView.askStatusOrEnd();
        if (command.isStatus()) {
            resultView.printResult(new ChessResultDto(new Scoreboard(chessboard), chessboard.winner()));
        }
    }
}
