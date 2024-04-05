import chess.ChessGame;
import chess.domain.chessboard.ChessboardRepository;
import chess.fen.dao.FenDao;
import chess.view.InputView;
import chess.view.ResultView;

public class Application {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(
                new InputView(), new ResultView(), new ChessboardRepository(new FenDao())
        );
        chessGame.run();
    }
}
