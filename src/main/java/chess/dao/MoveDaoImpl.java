package chess.dao;

import chess.database.DBConnectionUtil;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveDaoImpl implements MoveDao {

    @Override
    public void save(MoveRequest moveRequest) {
        String query = "INSERT INTO move (source, target, chess_game_id) VALUES(?, ?, ?)";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveRequest.source());
            preparedStatement.setString(2, moveRequest.target());
            preparedStatement.setLong(3, moveRequest.chessGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveResponse> findAll(Long id) {
        String query = "SELECT * FROM move WHERE chess_game_id = (?)";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getMoveResponses(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MoveResponse> getMoveResponses(ResultSet resultSet) throws SQLException {
        List<MoveResponse> pieceResponses = new ArrayList<>();
        while (resultSet.next()) {
            pieceResponses.add(new MoveResponse(
                    resultSet.getLong("id"),
                    resultSet.getString("source"),
                    resultSet.getString("target"),
                    resultSet.getLong("chess_game_id")
            ));
        }
        return pieceResponses;
    }
}
