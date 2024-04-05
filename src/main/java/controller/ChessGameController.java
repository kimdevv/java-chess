package controller;

import command.commandExecutor.CommandExecutor;
import java.sql.SQLException;
import java.util.List;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.base.ChessGameState;
import state.chessGame.statusfactory.ChessStatusFactory;
import view.InputView;
import view.OutputView;

public class ChessGameController {

    private final ChessGameService chessGameService;
    private final PieceService pieceService;

    public ChessGameController(ChessGameService chessGameService, PieceService pieceService) {
        this.chessGameService = chessGameService;
        this.pieceService = pieceService;
    }

    public void run() throws SQLException {
        ChessGameState chessGameState = ChessStatusFactory.initChessGame();
        CommandExecutor commandExecutor = new CommandExecutor(chessGameService, pieceService);

        OutputView.printGameGuide();
        do {
            List<String> inputCommand = InputView.receiveCommands();
            chessGameState = commandExecutor.executeCommand(inputCommand, chessGameState);
            chessGameState.show();
        } while (chessGameState.isPlaying());

        if (chessGameState.isKingCaught()) {
            pieceService.deletePieces(chessGameState);
            chessGameService.stopChessGame(chessGameState);
        }
    }
}
