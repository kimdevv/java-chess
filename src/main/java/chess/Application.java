package chess;

import chess.controller.ChessController;
import chess.dao.ChessDaoService;
import chess.dao.PieceDaoImpl;
import chess.dao.TurnDaoImpl;
import chess.db.JdbcTemplate;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        final JdbcTemplate jdbcTemplate = new JdbcTemplate();
        final ChessDaoService chessDaoService = new ChessDaoService(
                new PieceDaoImpl(jdbcTemplate),
                new TurnDaoImpl(jdbcTemplate)
        );

        final ChessController controller = new ChessController(chessDaoService, inputView, outputView);
        controller.run();
    }
}
