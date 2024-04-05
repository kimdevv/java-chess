package command.commandExecutor;

import command.End;
import command.Load;
import command.Move;
import command.Start;
import command.Status;
import command.base.Command;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.base.ChessGameState;

public class CommandExecutor {

    private final Map<String, Command> commandExecutor;

    public CommandExecutor(ChessGameService chessGameService, PieceService pieceService) {
        this.commandExecutor = new HashMap<>();
        initCommands(chessGameService, pieceService);
    }

    private void initCommands(ChessGameService chessGameService, PieceService pieceService) {
        commandExecutor.put("start", new Start(chessGameService, pieceService));
        commandExecutor.put("load", new Load(chessGameService, pieceService));
        commandExecutor.put("move", new Move(chessGameService, pieceService));
        commandExecutor.put("status", new Status(chessGameService));
        commandExecutor.put("end", new End());
    }

    public ChessGameState executeCommand(List<String> inputCommand, ChessGameState chessGameState) throws SQLException {
        String commandIdentifier = inputCommand.get(0);
        Command command = commandExecutor.get(commandIdentifier);
        if (command == null) {
            throw new IllegalArgumentException("해당하는 Command가 없습니다.");
        }
        if (inputCommand.size() > 1) {
            return command.execute(chessGameState, inputCommand);
        }
        return command.execute(chessGameState);
    }
}
