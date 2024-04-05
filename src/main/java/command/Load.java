package command;

import command.base.Command;
import db.entity.dto.ChessGameDto;
import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.base.ChessGameState;
import state.chessGame.statusfactory.ChessStatusFactory;

public class Load implements Command {

    private final ChessGameService chessGameService;
    private final PieceService pieceService;

    public Load(ChessGameService chessGameService, PieceService pieceService) {
        this.chessGameService = chessGameService;
        this.pieceService = pieceService;
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState) {
        throw new UnsupportedOperationException("불러올 방 이름이 없습니다.");
    }

    @Override
    public ChessGameState execute(ChessGameState chessGameState, List<String> inputCommand) throws SQLException {
        ChessGameDto chessGameDto = chessGameService.loadChessGame(inputCommand.get(1));
        Map<Coordinate, ChessPiece> board = pieceService.loadBoard(chessGameDto.id());

        return ChessStatusFactory.makeRunningChessGame(chessGameDto.id(), board, chessGameDto.turn());
    }
}
