package controller;

import command.Command;
import dao.ChessGameDaoImpl;
import dao.UserDao;
import domain.ChessGame;
import domain.Turn;
import domain.board.ChessBoard;
import domain.board.ChessBoardInitializer;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Command command = inputView.readInitCommand();
        if (command.isEnd()) {
            return;
        }
        String roomName = inputView.readRoomName();
        ChessGameService chessGameService = new ChessGameService(roomName, new ChessGameDaoImpl(new UserDao()));

        ChessGame chessGame = makeChessGame(chessGameService, roomName);
        playGame(command, chessGameService, chessGame);
    }

    private ChessGame makeChessGame(ChessGameService chessGameService, String roomName) {
        ChessBoard chessBoard;
        Turn turn;
        try {
            chessBoard = new ChessBoard(chessGameService.loadBoard());
            turn = chessGameService.loadTurn();
            outputView.printLoadGame(roomName);
        } catch (IllegalArgumentException illegalArgumentException) {
            chessBoard = new ChessBoard(ChessBoardInitializer.init());
            turn = new Turn();
            outputView.printNewGame(roomName);
        }
        return new ChessGame(chessBoard, turn);
    }

    private void playGame(Command command, ChessGameService chessGameService, ChessGame chessGame) {
        while (command.isNotEnd() && chessGameService.isGameNotOver(chessGame)) {
            command.execute(chessGame, outputView);
            chessGameService.saveData(chessGame);
            command = inputView.readCommand();
        }
    }
}
