package command.base;

import java.sql.SQLException;
import java.util.List;
import state.chessGame.base.ChessGameState;

public interface Command {

    ChessGameState execute(ChessGameState chessGameState) throws SQLException;

    ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) throws SQLException;
}
