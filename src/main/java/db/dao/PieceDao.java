package db.dao;

import db.connection.DatabaseConnection;
import db.entity.Piece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    public void saveAll(Long gameId, List<Piece> pieces) throws SQLException {
        String query = "INSERT INTO piece " +
                "(id, game_id, color, piece_type, `row`, `column`) " +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, gameId);

            addInitPiece(statement, pieces);
            statement.executeBatch();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("데이터베이스 생성에 실패했습니다." + e.getMessage());
        }
    }

    public Long findIdByRowAndColumnAndGameId(Long gameId, String row, String column) {
        String query = "SELECT * FROM piece " +
                "WHERE game_id = ? AND `row` = ? AND `column` = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, gameId);
            statement.setString(2, row);
            statement.setString(3, column);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            connection.commit();
        } catch (SQLException e) {
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("데이터베이스 생성에 실패했습니다." + e.getMessage());
        }
        throw new IllegalArgumentException("id를 찾지 못했습니다.");
    }

    public void updatePieceByRowAndColumn(Long id, String row, String column) throws SQLException {
        String query = "UPDATE piece " +
                "SET `row` = ?, `column` = ? " +
                "WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, row);
            statement.setString(2, column);
            statement.setLong(3, id);
            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("데이터베이스 생성에 실패했습니다." + e.getMessage());
        }
    }

    public void updatePieceByRowAndColumnAndPieceType(Long id, String row, String column) throws SQLException {
        String query = "UPDATE piece " +
                "SET `row` = ?, `column` = ?, piece_type = 'BLANK', color = 'NONE'" +
                "WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, row);
            statement.setString(2, column);
            statement.setLong(3, id);
            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("데이터베이스 생성에 실패했습니다." + e.getMessage());
        }
    }

    public void delete(Long gameId) throws SQLException {
        String query = "DELETE FROM piece " +
                "WHERE game_id = ?";

        Connection connection = DatabaseConnection.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, gameId);
            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("데이터베이스 삭제에 실패했습니다." + e.getMessage());
        }
    }

    private void addInitPiece(PreparedStatement statement, List<Piece> pieces)
            throws SQLException {

        for (Piece piece : pieces) {
            statement.setString(2, piece.getColorType());
            statement.setString(3, piece.getPieceType());
            statement.setString(4, piece.getRow());
            statement.setString(5, piece.getColumn());

            statement.addBatch();
        }
    }

    public List<Piece> findAllByGameId(Long gameId) throws SQLException {
        String query = "SELECT * " +
                "FROM piece " +
                "WHERE game_id = ? " +
                "ORDER BY `row` DESC, `column` ASC";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, gameId);

            List<Piece> pieces = new ArrayList<>();
            try (ResultSet result = statement.executeQuery()) {
                addPiece(gameId, result, pieces);
                return pieces;
            }
        } catch (SQLException e) {
            DatabaseConnection.tryCloseConnection();
            throw new RuntimeException("체스 판을 불러오는데 실패했습니다." + e.getMessage());
        }
    }

    private static void addPiece(Long gameId, ResultSet result, List<Piece> pieces) throws SQLException {
        while (result.next()) {
            String color = result.getString("color");
            String pieceType = result.getString("piece_type");
            String row = result.getString("row");
            String column = result.getString("column");

            pieces.add(Piece.createPiece(gameId, pieceType, color, row, column));
        }
    }
}
