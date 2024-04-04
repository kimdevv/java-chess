package service;

import dao.ChessGameDao;
import dao.mapper.BoardDaoMapper;
import dao.mapper.TurnDaoMapper;
import domain.ChessGame;
import domain.Turn;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public class ChessGameService {

    private final String roomName;
    private final ChessGameDao chessGameDao;

    public ChessGameService(String roomName, ChessGameDao chessGameDao) {
        this.roomName = roomName;
        this.chessGameDao = chessGameDao;
    }

    public Map<Position, Piece> loadBoard() {
        String data = chessGameDao.dataToBoard(roomName);
        return BoardDaoMapper.dataToBoard(data);
    }

    public Turn loadTurn() {
        String data = chessGameDao.dataToTurn(roomName);
        return TurnDaoMapper.dataToTurn(data);
    }

    public void resetBoard() {
        chessGameDao.deleteBoard(roomName);
    }

    public void saveData(ChessGame chessGame) {
        chessGameDao.deleteBoard(roomName);
        String boardData = BoardDaoMapper.boardToData(chessGame.chessBoard().getBoard());
        String turnData = TurnDaoMapper.turnToData(chessGame.turn());
        chessGameDao.saveData(roomName, boardData, turnData);
    }

    public boolean isGameNotOver(ChessGame chessGame) {
        if (chessGame.isAllKingAlive()) {
            return true;
        }
        resetBoard();
        return false;
    }
}
