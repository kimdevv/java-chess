package chess.service;

import chess.dao.ChessBoardDao;
import chess.dao.TurnDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import chess.dto.ChessBoardDto;
import java.util.List;

public class ChessGameService {

    private static final int MOVE_SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private final TurnDao turnDao = new TurnDao();
    private final ChessBoardDao chessBoardDao = new ChessBoardDao();

    public ChessGameService() {
    }

    public void initialSetting() {
        if (!turnDao.isExistenceTurn()) {
            turnDao.initialTurn();
            chessBoardDao.createChessBoard(new ChessBoardDto(ChessBoard.initialBoard()));
        }
    }

    public ChessBoard chessBoardSetting() {
        if (!turnDao.findTurn().isNoneCamp()) {
            return chessBoardDao.findChessBoard();
        }
        return ChessBoard.initialBoard();
    }

    public void updateChessGame(ChessBoard chessBoard, List<Square> moveSquare) {
        ChessPiece sourceChessPiece = chessBoard.findChessPieceOnSquare(moveSquare.get(MOVE_SOURCE_SQUARE_INDEX));
        ChessPiece targetChessPiece = chessBoard.findChessPieceOnSquare(moveSquare.get(TARGET_SQUARE_INDEX));
        chessBoardDao.updateChessBoardPiecePostion(moveSquare.get(MOVE_SOURCE_SQUARE_INDEX),
                moveSquare.get(TARGET_SQUARE_INDEX),
                sourceChessPiece.getChessPieceType());
        chessBoardDao.updateChessBoardPiecePostion(moveSquare.get(TARGET_SQUARE_INDEX),
                moveSquare.get(MOVE_SOURCE_SQUARE_INDEX),
                targetChessPiece.getChessPieceType());
        turnDao.updateTurn(sourceChessPiece.getCamp());
    }

    public void resetChessGame() {
        turnDao.deleteTurn();
        chessBoardDao.deleteChessBoard();
    }
}
