package chess.dao;

import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import java.util.Optional;

public interface ChessGameDao {

    Long save(ChessGameRequest chessGameRequest);

    Optional<ChessGameResponse> findRecentPlayableGame();

    void updateGameFinished();
}
