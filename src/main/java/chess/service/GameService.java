package chess.service;

import chess.domain.board.BoardStatus;
import chess.domain.game.GameCreator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class GameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void save(List<Piece> board, BoardStatus boardStatus) {
        String convertedBoard = BoardConverter.convertToString(board);
        String convertedBoardStatus = BoardStatusConverter.convertToString(boardStatus);

        gameDao.save(convertedBoard, convertedBoardStatus);
    }

    public GameCreator load() {
        BoardData boardData = gameDao.find();
        String convertedBoard = boardData.pieces();
        String convertedBoardStatus = boardData.status();

        Map<Position, Piece> board = BoardConverter.convertToBoard(convertedBoard);
        BoardStatus boardStatus = BoardStatusConverter.convertToBoardStatus(convertedBoardStatus);

        return new GameCreator() {
            @Override
            public Map<Position, Piece> createBoard() {
                return board;
            }

            @Override
            public BoardStatus createBoardStatus() {
                return boardStatus;
            }
        };
    }
}
