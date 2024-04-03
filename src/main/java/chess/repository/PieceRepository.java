package chess.repository;

import chess.repository.entity.PieceEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceRepository {
    private static final String TABLE_NAME = "pieces";

    private final ConnectionManager connectionManager;

    public PieceRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void savePieces(List<PieceEntity> pieceEntities) {
        try (Connection connection = connectionManager.getConnection()) {
            pieceEntities.forEach(piece -> savePiece(piece, connection));
        } catch (SQLException e) {
            throw new RuntimeException("기물 저장 과정 중 오류 발생");
        }
    }

    private void savePiece(PieceEntity pieceEntity, Connection connection) {
        String query = String.format("INSERT INTO %s (position, team, type) VALUES (?, ?, ?)", TABLE_NAME);

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, pieceEntity.getPosition());
            pstmt.setString(2, pieceEntity.getTeam());
            pstmt.setString(3, pieceEntity.getType());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("기물 저장 과정 중 오류 발생");
        }
    }

    public Optional<List<PieceEntity>> findAll() {
        String query = String.format("SELECT * FROM %s", TABLE_NAME);

        List<PieceEntity> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String team = resultSet.getString("team");
                String type = resultSet.getString("type");
                result.add(new PieceEntity(position, team, type));
            }
            if (result.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(result);
        } catch (SQLException e) {
            throw new RuntimeException("보드 조회 과정 중 오류 발생");
        }
    }

    public void deleteAll() {
        String query = String.format("DELETE FROM %s", TABLE_NAME);

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("기물 조회 과정 중 오류 발생");
        }
    }
}
