package chess.domain;

import chess.DatabaseConnection;
import chess.domain.board.Board;
import chess.domain.dao.MysqlChessRoomDao;
import chess.domain.dao.MysqlPieceDao;
import chess.domain.piece.Piece;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.service.ChessService;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private static final String DATABASE_NAME = "chess";

    private final ChessService chessService;
    private Board board;
    private Team turn;

    public ChessGame(Long chess_room_id) {
        Connection connection = DatabaseConnection.getConnection(DATABASE_NAME);

        chessService = new ChessService(chess_room_id, new MysqlPieceDao(connection),
                new MysqlChessRoomDao(connection));
        this.board = new Board(new HashMap<>());
        this.turn = Team.NONE;
    }

    public boolean isNotFinish() {
        return !board.isKingDead();
    }

    public void initializeData() {
        chessService.initializeChess();

    }

    public void loadData() {
        board = chessService.loadPieces();
        turn = chessService.loadTurn();
    }

    public void movePiece(Position target, Position source) {
        board.movePiece(target, source, turn);
        turn = Team.takeTurn(turn);
    }

    public Map<Team, Double> calculatePiecesScoreSum() {
        Map<Team, Double> scores = new HashMap<>();

        scores.put(Team.BLACK, board.calculatePiecesScoreSum(Team.BLACK));
        scores.put(Team.WHITE, board.calculatePiecesScoreSum(Team.WHITE));

        return scores;
    }

    public void saveData() {
        chessService.savePieces(board);
        chessService.saveTurn(turn);
    }

    public void deleteData() {
        chessService.deletePieces();
    }

    public String getRawTurn() {
        return turn.getRawTeam();
    }

    public Map<Position, Piece> getRawBoard() {
        return board.getBoard();
    }
}
