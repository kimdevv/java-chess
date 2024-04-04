package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceType;
import chess.dto.ChessBoardDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoardDao {

    private final DBConnection dbConnection = new DBConnection();

    public void createChessBoard(final ChessBoardDto chessBoardDto) {
        final var query = "INSERT INTO chessBoard VALUES(?, ?, ?, ?)";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Entry<Square, ChessPiece> entry : chessBoardDto.chessBoard().entrySet()) {
                preparedStatement.setString(1, entry.getValue().getCamp().toString());
                preparedStatement.setInt(2, entry.getKey().getLettering().getValue());
                preparedStatement.setInt(3, entry.getKey().getNumbering().getValue());
                preparedStatement.setString(4, entry.getValue().getChessPieceType().toString());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateChessBoardPiecePostion(Square moveSource, Square target, ChessPieceType chessPieceType) {
        final var query = "UPDATE chessBoard SET lettering = ?, numbering = ? "
                + "WHERE chessPieceType = ? AND lettering = ? AND numbering = ?";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, target.getLettering().getValue());
            preparedStatement.setInt(2, target.getNumbering().getValue());
            preparedStatement.setString(3, chessPieceType.toString());
            preparedStatement.setInt(4, moveSource.getLettering().getValue());
            preparedStatement.setInt(5, moveSource.getNumbering().getValue());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessBoard findChessBoard() {
        final var query = "SELECT * FROM chessBoard";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            Map<Square, ChessPiece> queriedChessBoard = new LinkedHashMap<>();
            while (resultSet.next()) {
                Square square = squareDeserialization(resultSet);
                ChessPiece chessPiece = chessPieceDeserialization(resultSet);
                queriedChessBoard.put(square, chessPiece);
            }
            return ChessBoard.from(queriedChessBoard);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Square squareDeserialization(final ResultSet resultSet) throws SQLException {
        Lettering lettering = Lettering.findLettering(resultSet.getInt("lettering"));
        Numbering numbering = Numbering.findNumbering(resultSet.getInt("numbering"));
        return new Square(lettering, numbering);
    }

    private ChessPiece chessPieceDeserialization(final ResultSet resultSet) throws SQLException {
        Camp camp = Camp.findCamp(resultSet.getString("camp"));
        ChessPieceType chessPieceType = ChessPieceType.findChessPieceType(
                resultSet.getString("chessPieceType"));
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(chessPieceType,
                chessPieceType.getMoveStrategy());
        return ChessPiece.of(camp, chessPieceProperty);
    }

    public void deleteChessBoard() {
        final var query = "DELETE FROM chessBoard WHERE true";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
