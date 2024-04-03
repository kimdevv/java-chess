package chess;

import chess.controller.ChessController;
import chess.dao.MoveJdbcDao;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;

public class Application {
    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService(new MoveJdbcDao());
        ChessController chessController = new ChessController(chessGameService);

        chessController.run();
    }
}
