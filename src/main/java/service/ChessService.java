package service;

import dao.ChessBoardDao;
import dao.TurnDao;
import domain.ChessBoard;
import domain.Square;
import domain.Team;
import domain.piece.Piece;

import java.sql.SQLException;
import java.util.Map;

public class ChessService {
    private ChessBoard chessBoard;
    private final ChessBoardDao chessBoardDao;
    private final TurnDao turnDao;

    public ChessService(final ChessBoardDao chessBoardDao, final TurnDao turnDao) {
        this.chessBoardDao = chessBoardDao;
        this.turnDao = turnDao;
    }

    public void start() {
        final Map<Square, Piece> pieces = chessBoardDao.findAll();
        final Team team = turnDao.find();
        chessBoard = new ChessBoard(pieces, team);
    }

    public ChessBoard move(final Square source, final Square target) {
        chessBoard.move(source, target);
        return chessBoard;
    }

    public Map<Team, Double> status() {
        return chessBoard.status();
    }

    public boolean isFinished() {
        return chessBoard.isFinished();
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard.getPieces();
    }

    public void end() throws SQLException {
        chessBoardDao.update(chessBoard);
        turnDao.update(chessBoard.currentTeam());
    }
}
