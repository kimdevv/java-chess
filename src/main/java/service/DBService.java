package service;

import dao.DBConnector;
import dao.DBException;
import dao.GameDao;
import dao.GameDaoImpl;
import dao.PieceDao;
import dao.PieceDaoImpl;
import domain.game.ChessGame;
import domain.game.Piece;
import domain.game.PieceFactory;
import domain.game.TeamColor;
import domain.position.Position;
import dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBService {
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    protected DBService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public DBService() {
        this(new GameDaoImpl(), new PieceDaoImpl());
    }

    private Connection getConnectionOfAutoCommit(boolean autoCommit) {
        Connection connection = DBConnector.getInstance().getConnection();
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return connection;
    }

    public ChessGame loadGame(int gameId) {
        Connection connection = getConnectionOfAutoCommit(true);
        TeamColor savedTurn = gameDao.findTurn(connection, gameId);
        List<PieceDto> savedPieces = pieceDao.findAllPieces(connection, gameId);
        return ChessGame.of(savedTurn, separatePositionAndPiece(savedPieces));
    }

    private Map<Position, Piece> separatePositionAndPiece(List<PieceDto> savedPieces) {
        return savedPieces.stream()
                .collect(Collectors.toMap(
                        PieceDto::getPosition,
                        dto -> PieceFactory.create(dto.getPieceType())
                ));
    }

    public int saveGame(ChessGame chessGame) {
        Connection connection = getConnectionOfAutoCommit(false);
        try (connection) {
            int gameId = gameDao.addGame(connection);
            gameDao.updateTurn(connection, gameId, chessGame.currentPlayingTeam());
            pieceDao.addAll(connection, collectPositionOfPieces(chessGame), gameId);
            connection.commit();
            return gameId;
        } catch (SQLException | DBException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new IllegalStateException("트랜잭션 롤백 중 오류가 발생했습니다.", rollbackException);
            }
            throw new DBException("게임 저장 중 오류가 발생했습니다.", e);
        }
    }

    private List<PieceDto> collectPositionOfPieces(ChessGame chessGame) {
        return chessGame.getPositionsOfPieces().entrySet().stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}
