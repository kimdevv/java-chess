package application;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.board.Winner;
import domain.position.Position;
import dto.MovementDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistence.BoardDao;

public class BoardService {
    private final BoardDao boardDao;
    private final Map<Integer, ChessBoard> chessBoards;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
        this.chessBoards = new HashMap<>();
    }

    public void loadBoard(int roomId) {
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();
        List<MovementDto> movementDtos = boardDao.findByRoomId(roomId);
        for (MovementDto movementDto : movementDtos) {
            Position source = Position.from(movementDto.source());
            Position target = Position.from(movementDto.target());
            chessBoard.movePiece(source, target);
        }
        chessBoards.put(roomId, chessBoard);
    }

    public void move(MovementDto movementDto, int roomId) {
        ChessBoard chessBoard = getChessBoard(roomId);
        Position source = Position.from(movementDto.source());
        Position target = Position.from(movementDto.target());
        chessBoard.movePiece(source, target);
        boardDao.save(movementDto, roomId);
    }

    public ChessBoard getChessBoard(int roomId) {
        if (!chessBoards.containsKey(roomId)) {
            throw new IllegalArgumentException("보드가 존재하지 않습니다.");
        }
        return chessBoards.get(roomId);
    }

    public boolean isGameOver(int roomId) {
        ChessBoard chessBoard = getChessBoard(roomId);
        return chessBoard.isKingCaptured();
    }

    public Winner getWinner(int roomId) {
        ChessBoard chessBoard = getChessBoard(roomId);
        return chessBoard.getWinner();
    }

    public void removeBoard(int roomId) {
        chessBoards.remove(roomId);
        boardDao.deleteByRoomId(roomId);
    }
}
