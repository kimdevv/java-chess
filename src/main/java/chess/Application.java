package chess;

import chess.controller.ChessGameController;
import chess.dao.ChessGameDaoImpl;
import chess.dao.MoveDaoImpl;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.ChessGameService;

public class Application {
    public static void main(String[] args) {
        new ChessGameController(new ChessGameService(new ChessGameDaoImpl(), new MoveDaoImpl()),
                new ChessGame(BoardFactory.createInitialBoard())).run();
    }
}
