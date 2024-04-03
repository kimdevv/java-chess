package controller;

import dao.ChessBoardDao;
import dao.MySqlConnectionPool;
import dao.TurnDao;
import domain.File;
import domain.Rank;
import domain.Square;
import dto.ChessBoardDto;
import dto.StatusDto;
import service.ChessService;
import view.InputView;
import view.OutputView;

import java.sql.SQLException;

public class ChessController {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final ChessService chessService;

    public ChessController() {
        chessService = createChessService();
    }

    private ChessService createChessService() {
        final MySqlConnectionPool mySqlConnectionPool = new MySqlConnectionPool();
        final ChessBoardDao chessBoardDao = new ChessBoardDao(mySqlConnectionPool);
        final TurnDao turnDao = new TurnDao(mySqlConnectionPool);
        return new ChessService(chessBoardDao, turnDao);
    }

    public void run() {
        OUTPUT_VIEW.printHeader();
        Status status = Status.READY;
        while (status.isContinue()) {
            try {
                final Menu menu = INPUT_VIEW.readMenu();
                status = execute(menu);
            } catch (final Exception e) {
                OUTPUT_VIEW.printError(e.getMessage());
            }
        }
        OUTPUT_VIEW.printEndMessage();
    }

    public Status execute(final Menu menu) throws SQLException {
        if (menu.isStart()) {
            return startGame();
        }
        if (menu.isMove()) {
            return playGame();
        }
        if (menu.isStatus()) {
            return printStatus();
        }
        if (menu.isEnd()) {
            return endGame();
        }
        throw new IllegalStateException("예기치 못한 오류가 발생했습니다.");
    }

    private Status startGame() {
        chessService.start();
        printChessBoard();
        return Status.RUNNING;
    }

    private Status playGame() {
        final Square source = readSquare();
        final Square target = readSquare();
        chessService.move(source, target);

        if (chessService.isFinished()) {
            return Status.END;
        }

        printChessBoard();
        return Status.RUNNING;
    }

    private Square readSquare() {
        final MoveCommand moveCommand = MoveCommand.fromInput(new InputView().readMoveCommand());
        return new Square(File.from(moveCommand.file()), Rank.from(moveCommand.rank()));
    }

    private Status printStatus() {
        final StatusDto statusDto = StatusDto.from(chessService.status());
        OUTPUT_VIEW.printStatus(statusDto);
        return Status.RUNNING;
    }

    private void printChessBoard() {
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessService.getChessBoard());
        OUTPUT_VIEW.printChessBoard(chessBoardDto);
    }

    private Status endGame() throws SQLException {
        chessService.end();
        return Status.END;
    }
}
