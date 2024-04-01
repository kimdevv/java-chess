package chess.dao;

import chess.database.DBConnectionUtil;
import chess.domain.GameStatus;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChessGameDaoImpl implements ChessGameDao {

    @Override
    public Long save(ChessGameRequest chessGameRequest) {
        String query = "INSERT INTO chess_game (game_status) VALUES (?)";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chessGameRequest.gameStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            return getSavedGameId(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getSavedGameId(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getLong(1);
        }
        throw new IllegalStateException("게임 저장을 실패했습니다.");
    }

    @Override
    public Optional<ChessGameResponse> findRecentPlayableGame() {
        String query = "SELECT * FROM chess_game WHERE game_status = ? ORDER BY id DESC LIMIT 1";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, GameStatus.PLAYING.name());
            ResultSet resultSet = preparedStatement.executeQuery();

            return getChessGameResponse(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<ChessGameResponse> getChessGameResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            long id = resultSet.getLong("id");
            String status = resultSet.getString("game_status");
            return Optional.of(new ChessGameResponse(id, status));
        }
        return Optional.empty();
    }

    @Override
    public void updateGameFinished() {
        String query = "UPDATE chess_game SET game_status = ?";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, GameStatus.FINISHED.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
