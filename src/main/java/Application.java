import application.BoardService;
import application.RoomService;
import controller.GateController;
import controller.board.BoardController;
import controller.room.RoomController;
import database.DefaultConnectionPool;
import database.JdbcTemplate;
import persistence.BoardDaoImpl;
import persistence.RoomDaoImpl;
import ui.InputView;
import ui.output.BoardOutputView;
import ui.output.GateOutputView;
import ui.output.RoomOutputView;

public class Application {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultConnectionPool());
        InputView inputView = new InputView();
        GateController gateController = createGateController(inputView, jdbcTemplate);
        gateController.run();
    }

    private static GateController createGateController(InputView inputView, JdbcTemplate jdbcTemplate) {
        RoomController roomController = createRoomController(jdbcTemplate, inputView);
        return new GateController(inputView, new GateOutputView(), roomController);
    }

    private static RoomController createRoomController(JdbcTemplate jdbcTemplate, InputView inputView) {
        RoomService roomService = new RoomService(new RoomDaoImpl(jdbcTemplate));
        BoardController boardController = createBoardController(jdbcTemplate, inputView);
        return new RoomController(inputView, new RoomOutputView(), roomService, boardController);
    }

    private static BoardController createBoardController(JdbcTemplate jdbcTemplate, InputView inputView) {
        BoardService boardService = new BoardService(new BoardDaoImpl(jdbcTemplate));
        return new BoardController(inputView, new BoardOutputView(), boardService);
    }
}
