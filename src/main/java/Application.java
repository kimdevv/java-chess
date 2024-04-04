import controller.ChessGameController;
import view.InputView;
import view.MessageResolver;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new InputView(), new OutputView(new MessageResolver()));
        chessGameController.run();
    }
}
