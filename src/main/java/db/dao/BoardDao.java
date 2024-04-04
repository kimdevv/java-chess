package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.dto.CoordinateDto;

public class BoardDao {

    private static final BoardDao instance = new BoardDao();
    private static final ChessDBUtil chessDBUtil = ChessDBUtil.getInstance();

    private BoardDao() {
    }

    public static BoardDao getInstance() {
        return instance;
    }

    public void createBoard(List<CoordinateDto> coordinateDtos) {
        final String query = "INSERT INTO board VALUES(?, ?, ?)";

        try (final Connection connection = chessDBUtil.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            coordinateDtos.forEach(coordinate -> insertPiece(coordinate, preparedStatement));
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.clearBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertPiece(CoordinateDto coordinateDto, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, coordinateDto.coordinate());
            preparedStatement.setString(2, coordinateDto.pieceType());
            preparedStatement.setString(3, coordinateDto.pieceColor());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CoordinateDto> loadBoard() {
        final String query = "SELECT coordinate, piece_type, piece_color FROM board";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getBoard(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CoordinateDto> getBoard(ResultSet resultSet) throws SQLException {
        List<CoordinateDto> coordinates = new ArrayList<>();
        while (resultSet.next()) {
            coordinates.add(new CoordinateDto(
                    resultSet.getString("coordinate"),
                    resultSet.getString("piece_type"),
                    resultSet.getString("piece_color")
            ));
        }
        if (coordinates.isEmpty()) {
            throw new IllegalStateException("게임이 존재하지 않습니다.");
        }
        return coordinates;
    }

    public void updateBoard(CoordinateDto start, CoordinateDto destination) {
        final String query = "UPDATE board SET piece_type = ?, piece_color = ? WHERE coordinate = ?";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            update(start, preparedStatement);
            update(destination, preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(CoordinateDto coordinateDto, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, coordinateDto.pieceType());
            preparedStatement.setString(2, coordinateDto.pieceColor());
            preparedStatement.setString(3, coordinateDto.coordinate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        final String query = "DELETE FROM board";
        try (final Connection connection = chessDBUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
