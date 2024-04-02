package chess.dao;

import static chess.domain.GameStatus.GAME_OVER;
import static chess.domain.GameStatus.WHITE_TURN;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardGenerator;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoardDao {
    private final Connection connection;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessBoardDao(Connection connection) {
        this.connection = connection;
        this.chessGameDao = new ChessGameDao(connection);
        this.pieceDao = new PieceDao(connection);
    }

    public void addChessBoard(ChessBoard chessBoard) {
        for(Entry<Position, Piece> position : chessBoard.getChessBoard().entrySet()) {
            addPosition(chessGameDao.findId(), position.getKey(), position.getValue());
        }
    }

    private void addPosition(long gameId, Position position, Piece piece) {
        final var query = "INSERT INTO board(game_id, position, piece_id) VALUES(?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, gameId);
            preparedStatement.setString(2, PositionConverter.convertToString(position));
            preparedStatement.setByte(3, pieceDao.findIdByPiece(piece));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessBoard loadChessBoard() {
        final var query = "SELECT position, piece_id FROM board WHERE game_id = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, chessGameDao.findId());
            final var resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> chessBoard = new HashMap<>();
            while(resultSet.next()) {
                Position position = PositionConverter.convertToPosition(resultSet.getString("position"));
                Piece piece = pieceDao.findPieceById(resultSet.getByte("piece_id"));
                chessBoard.put(position, piece);
            }
            return new ChessBoard(chessBoard);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE FROM board";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void movePiece(Position source, Position target) {
        delete(target);
        updateBoard(source, target);
    }

    private void delete(Position position) {
        final var query = "DELETE FROM board WHERE position = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, PositionConverter.convertToString(position));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateBoard(Position source, Position target) {
        final var query = "UPDATE board SET position = ? WHERE position = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, PositionConverter.convertToString(target));
            preparedStatement.setString(2, PositionConverter.convertToString(source));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
