package chess;

import static chess.domain.Command.STATUS;
import static chess.domain.GameStatus.GAME_OVER;
import static chess.domain.GameStatus.WHITE_TURN;

import chess.domain.Command;
import chess.domain.GameStatus;
import chess.domain.chessboard.ChessBoard;
import chess.dao.ChessBoardDao;
import chess.dao.ChessGameDao;
import chess.dao.ConnectionGenerator;
import chess.domain.chessboard.ChessBoardGenerator;
import chess.domain.position.Position;
import chess.util.RetryUtil;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.Connection;
import java.util.List;

public class ChessGame {
    private final ChessGameDao chessGameDao;
    private final ChessBoardDao chessBoardDao;
    private GameStatus gameStatus;

    public ChessGame() {
        Connection connection = ConnectionGenerator.getConnection();
        chessGameDao = new ChessGameDao(connection);
        chessBoardDao = new ChessBoardDao(connection);
    }

    public void run() {
        OutputView.printStartMessage();
        RetryUtil.read(() -> Command.getStartCommand(InputView.readCommand()));
        ChessBoard chessBoard = loadChessBoard();

        while (gameStatus != GAME_OVER) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
            gameStatus = RetryUtil.read(() -> processGame(chessBoard));
            chessGameDao.updateGameStatus(gameStatus);
        }
        OutputView.printGameOverMessage();

        if(RetryUtil.read(() -> Command.getClosingCommand(InputView.readCommand())) == STATUS) {
            OutputView.printScore(chessBoard.calculateTotalScore());
        }
    }

    private ChessBoard loadChessBoard() {
        ChessBoard chessBoard = chessBoardDao.loadChessBoard();
        gameStatus = chessGameDao.findGameStatus();

        if(chessBoard.isEmpty() || gameStatus == GAME_OVER) {
            chessBoard = new ChessBoard(ChessBoardGenerator.initializeBoard());
            chessBoardDao.deleteAll();
            chessBoardDao.addChessBoard(chessBoard);
            chessGameDao.updateGameStatus(WHITE_TURN);
            gameStatus = chessGameDao.findGameStatus();
        }

        return chessBoard;
    }

    private GameStatus processGame(ChessBoard chessBoard) {
        Command command = Command.getProcessCommand(InputView.readCommand());
        if (command.isMove()) {
            List<String> sourcePosition = InputView.readPosition();
            Position source = new Position(sourcePosition.get(0), sourcePosition.get(1));
            List<String> targetPosition = InputView.readPosition();
            Position target = new Position(targetPosition.get(0), targetPosition.get(1));

            gameStatus = chessBoard.move(source, target, gameStatus);
            chessBoardDao.movePiece(source, target);

            return gameStatus;
        }
        return GAME_OVER;
    }
}
