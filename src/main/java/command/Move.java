package command;

import command.base.Command;
import java.sql.SQLException;
import java.util.List;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.base.ChessGameState;

public class Move implements Command {

    private final PieceService pieceService;
    private final ChessGameService chessGameService;

    public Move(ChessGameService chessGameService, PieceService pieceService) {
        this.pieceService = pieceService;
        this.chessGameService = chessGameService;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState) {
        throw new UnsupportedOperationException("움직일 위치를 입력받지 않았습니다.");
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) throws SQLException {
        chessGameState = pieceService.updatePiece(inputCommand, chessGameState);
        if (chessGameState.isKingCaught()) {
            return chessGameState;
        }
        chessGameService.switchTurn(chessGameState);
        return chessGameState;
    }
}
