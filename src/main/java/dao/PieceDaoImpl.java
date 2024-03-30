package dao;

import dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {
    private static final String TABLE_NAME = "piece";

    @Override
    public void addAll(Connection connection, List<PieceDto> pieceDtos, int gameId) {
        final String query = String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?)", TABLE_NAME);
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (PieceDto pieceDto : pieceDtos) {
                pstmt.setInt(1, pieceDto.fileIndex());
                pstmt.setInt(2, pieceDto.rankIndex());
                pstmt.setString(3, pieceDto.color());
                pstmt.setString(4, pieceDto.type());
                pstmt.setInt(5, gameId);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<PieceDto> findAllPieces(Connection connection, int gameId) {
        final List<PieceDto> pieces = new ArrayList<>();
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE gameId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int fileIndex = resultSet.getInt("file");
                int rankIndex = resultSet.getInt("rank");
                String color = resultSet.getString("pieceColor");
                String type = resultSet.getString("pieceType");
                pieces.add(new PieceDto(fileIndex, rankIndex, color, type));
            }
        } catch (final SQLException e) {
            throw new DBException(e);
        }
        return pieces;
    }
}
