package chess;

import static chess.domain.Command.STATUS;
import static chess.domain.GameStatus.GAME_OVER;
import static chess.domain.GameStatus.WHITE_TURN;

import chess.domain.Command;
import chess.domain.GameStatus;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardGenerator;
import chess.domain.position.Position;
import chess.util.RetryUtil;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {
    private GameStatus gameStatus;

    public void run() {
        OutputView.printStartMessage();
        RetryUtil.read(() -> Command.getStartCommand(InputView.readCommand()));
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.initializeBoard());
        gameStatus = WHITE_TURN;
        while (gameStatus != GAME_OVER) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
            gameStatus = RetryUtil.read(() -> processGame(chessBoard));
        }
        OutputView.printGameOverMessage();

        if(RetryUtil.read(() -> Command.getClosingCommand(InputView.readCommand())) == STATUS) {
            OutputView.printScore(chessBoard.calculateTotalScore());
        }
    }

    private GameStatus processGame(ChessBoard chessBoard) {
        Command command = Command.getProcessCommand(InputView.readCommand());
        if (command.isMove()) {
            List<String> sourcePosition = InputView.readPosition();
            Position source = new Position(sourcePosition.get(0), sourcePosition.get(1));
            List<String> targetPosition = InputView.readPosition();
            Position target = new Position(targetPosition.get(0), targetPosition.get(1));

            return chessBoard.move(source, target, gameStatus);
        }
        return GAME_OVER;
    }
}
