package dao;

import domain.*;
import domain.piece.Piece;
import domain.piece.jumping.King;
import domain.piece.jumping.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {
    private final ConnectionPool connectionPool;

    public ChessBoardDao(final ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Map<Square, Piece> findAll() {
        final var query = "SELECT * FROM chessboard";
        final Connection connection = connectionPool.getConnection();

        try (final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            final Map<Square, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                final Square square = createSquare(resultSet);
                final Piece piece = createPiece(resultSet);
                pieces.put(square, piece);
            }
            connectionPool.releaseConnection(connection);
            return pieces;
        } catch (final SQLException e) {
            connectionPool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    private Square createSquare(final ResultSet resultSet) throws SQLException {
        final File file = File.from(resultSet.getString("file"));
        final Rank rank = Rank.from(Integer.parseInt(resultSet.getString("rank")));
        return new Square(file, rank);
    }

    private Piece createPiece(final ResultSet resultSet) throws SQLException {
        final String pieceName = resultSet.getString("piece");
        final Piece piece;
        final Team team = Team.from(resultSet.getString("team"));
        switch (pieceName) {
            case "king" -> piece = new King(team);
            case "queen" -> piece = new Queen(team);
            case "rook" -> piece = new Rook(team);
            case "bishop" -> piece = new Bishop(team);
            case "knight" -> piece = new Knight(team);
            default -> {
                if (team == Team.BLACK) {
                    piece = new BlackPawn();
                } else {
                    piece = new WhitePawn();
                }
            }
        }
        return piece;
    }

    public void update(final ChessBoard chessBoard) throws SQLException {
        final String deleteQuery = "DELETE FROM chessboard";
        final String insertQuery = "INSERT INTO chessboard (`piece`, `team`, `rank`, `file`) VALUES (?, ?, ?, ?)";
        final Connection connection = connectionPool.getConnection();

        try (final var deleteStatement = connection.prepareStatement(deleteQuery);
             final var insertStatement = connection.prepareStatement(insertQuery)) {
            connection.setAutoCommit(false);
            deleteStatement.executeUpdate();

            for (final Map.Entry<Square, Piece> entry : chessBoard.getPieces().entrySet()) {
                setInsertStatement(entry, insertStatement);
                insertStatement.executeUpdate();
            }
            connection.commit();
            connectionPool.releaseConnection(connection);
        } catch (final SQLException e) {
            connection.rollback();
            connectionPool.releaseConnection(connection);
            throw new RuntimeException(e);
        }
    }

    private void setInsertStatement(final Map.Entry<Square, Piece> entry, final PreparedStatement insertStatement) throws SQLException {
        final Square square = entry.getKey();
        final Piece piece = entry.getValue();
        if (piece instanceof BlackPawn || piece instanceof WhitePawn) {
            insertStatement.setString(1, "pawn");
        } else {
            insertStatement.setString(1, piece.getClass().getSimpleName().toLowerCase());
        }
        insertStatement.setString(2, piece.team().name().toLowerCase());
        insertStatement.setString(3, String.valueOf(square.rank().getIndex()));
        insertStatement.setString(4, square.file().name().toLowerCase());
    }
}
