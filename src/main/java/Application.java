import controller.ChessGameController;
import java.sql.SQLException;
import service.ChessGameService;
import service.PieceService;

public class Application {

    public static void main(String[] args) throws SQLException {
        ChessGameService chessGameService = new ChessGameService();
        PieceService pieceService = new PieceService();

        ChessGameController chessGameController = new ChessGameController(chessGameService, pieceService);

        chessGameController.run();
    }
}
