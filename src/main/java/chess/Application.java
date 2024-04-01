package chess;

import chess.dao.db.DatabaseChessDAO;
import chess.dao.db.MySQLConfiguration;
import chess.game.ChessGame;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in);
             var connection = MySQLConfiguration.getConnection()) {
            InputView inputView = new InputView(scanner);
            OutputView outputView = new OutputView();
            ChessService chessService = new ChessService(new DatabaseChessDAO(connection));
            
            ChessGame chessGame = new ChessGame();
            chessGame.start(inputView, outputView, chessService);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
