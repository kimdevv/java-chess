package command;

import command.base.Command;
import java.sql.SQLException;
import java.util.List;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.base.ChessGameState;
import view.InputView;

public class Start implements Command {

    private final ChessGameService chessGameService;
    private final PieceService pieceService;

    public Start(ChessGameService chessGameService, PieceService pieceService) {
        this.chessGameService = chessGameService;
        this.pieceService = pieceService;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState) throws SQLException {
        String roomName = InputView.receiveRoomName();
        chessGameState = chessGameService.addChessGame(roomName);
        pieceService.addPieces(chessGameState.getGameId(), chessGameState.getBoard());
        return chessGameState;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) {
        throw new IllegalArgumentException("잘못된 입력 값을 받았습니다.");
    }
}
