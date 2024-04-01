package chess;

import chess.dao.ChessGameDao;
import chess.domain.attribute.Color;
import chess.domain.chessboard.Chessboard;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessboardDto;
import chess.view.dto.GameResultDto;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;
    private final ChessGameDao chessGameDao;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.chessGameDao = new ChessGameDao();
    }

    public void run() {
        resultView.printGameStartMessage();
        String command = inputView.askCommand();
        if (Command.isStart(command)) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = Chessboard.of(chessGameDao.findAllPieces());
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard);
    }

    private void playByCommand(final Chessboard chessboard) {
        if (chessboard.catchKing()) {
            endGame(chessboard);
            return;
        }
        String command = inputView.askCommand();
        if (Command.isMove(command)) {
            moveChessPiece(chessboard, command);
            playByCommand(chessboard);
            return;
        }
        if (Command.isStatus(command)) {
            boardStatus(chessboard);
            initChessBoard(chessboard);
            return;
        }
        if (Command.isEnd(command)) {
            endGame(chessboard);
            initChessBoard(chessboard);
            return;
        }
        throw new IllegalArgumentException("잘못된 명령어 입니다.");
    }

    private void moveChessPiece(final Chessboard chessboard, String command) {
        MoveCommand moveCommand = new MoveCommand(command);
        chessboard.move(moveCommand.source(), moveCommand.target());
        if (chessGameDao.existPieceIn(moveCommand.target())) {
            chessGameDao.deletePieceOf(moveCommand.target());
        }
        chessGameDao.updatePieceMovement(moveCommand.source(), moveCommand.target());
        resultView.printBoard(new ChessboardDto(chessboard));
    }

    private void boardStatus(Chessboard chessboard) {
        resultView.printWinner(GameResultDto.of(chessboard.findWinner()));
        resultView.printStatus(Color.WHITE, chessboard.totalScoreOf(Color.WHITE));
        resultView.printStatus(Color.BLACK, chessboard.totalScoreOf(Color.BLACK));
        initChessBoard(chessboard);
    }

    private void endGame(Chessboard chessboard) {
        resultView.printWinner(GameResultDto.of(chessboard.findWinner()));
        initChessBoard(chessboard);
    }

    private void initChessBoard(Chessboard chessboard) {
        deleteChessboard(chessboard);
        createNewChessboard();
    }

    private void deleteChessboard(Chessboard chessboard) {
        chessboard.deleteAllPieces();
        chessGameDao.deleteAllPieces();
    }

    private void createNewChessboard() {
        chessGameDao.initChessboard();
    }
}
