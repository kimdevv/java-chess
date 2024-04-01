package chess.dao;

import chess.dao.convertor.PieceDto;
import chess.dao.convertor.PiecesConvertor;
import chess.domain.attribute.Square;
import chess.domain.chessboard.Chessboard;
import chess.domain.piece.Piece;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChessGameDao {

    private final JdbcConnection jdbcConnection;

    public ChessGameDao() {
        this.jdbcConnection = new JdbcConnection();
    }

    public void addPiece(final Piece piece) {
        final var query = "INSERT INTO pieces VALUES(?, ?, ?, ?)";
        String type = piece.getPieceType().toString();
        String color = piece.getColor().toString();
        Square square = piece.currentSquare();
        jdbcConnection.executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, square.getFile().toString());
            preparedStatement.setString(4, square.getRank().getValue());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    public void deleteAllPieces() {
        final var query = "DELETE FROM pieces";
        jdbcConnection.executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            return null;
        });
    }

    public void deletePieceOf(Square square) {
        final var query = "DELETE FROM pieces WHERE piece_file = ? AND piece_rank = ?";
        jdbcConnection.executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, square.getFile().toString());
            preparedStatement.setString(2, square.getRank().getValue());
            preparedStatement.executeUpdate();
            return null;
        });
    }

    public boolean existPieceIn(Square square) {
        final var query = "SELECT EXISTS("
                + "SELECT 1 FROM pieces WHERE piece_file = ? AND piece_rank = ?"
                + ") as cnt";
        return jdbcConnection.executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, square.getFile().toString());
            preparedStatement.setString(2, square.getRank().getValue());
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("cnt").equals("1");
            }
            return false;
        });
    }

    public void initChessboard() {
        Chessboard chessBoard = Chessboard.createChessBoard();
        Map<Square, Piece> chessboard = chessBoard.getChessboard();
        for (Piece piece : chessboard.values()) {
            addPiece(piece);
        }
    }

    public List<Piece> findAllPieces() {
        final var query = "SELECT * FROM pieces";
        return jdbcConnection.executeQuery(connection -> {
            List<Piece> pieces = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Piece piece = PiecesConvertor.mapToDomain(new PieceDto(resultSet.getString("piece_type"),
                        resultSet.getString("piece_team"),
                        resultSet.getString("piece_file"),
                        resultSet.getString("piece_rank")));
                pieces.add(piece);
            }
            return pieces;
        });
    }

    public void updatePieceMovement(Square prevSquare, Square movedSquare) {
        final var query = "UPDATE pieces SET piece_file = ?, piece_rank = ? WHERE piece_file = ? AND piece_rank = ?";
        jdbcConnection.executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movedSquare.getFile().toString());
            preparedStatement.setString(2, movedSquare.getRank().getValue());
            preparedStatement.setString(3, prevSquare.getFile().toString());
            preparedStatement.setString(4, prevSquare.getRank().getValue());
            preparedStatement.executeUpdate();
            return null;
        });
    }
}

