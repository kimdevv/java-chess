package chess.domain;

import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import chess.dto.Move;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final ChessGameDao chessGameDao;
    private final MoveDao moveDao;

    public ChessGameService(ChessGameDao chessGameDao, MoveDao moveDao) {
        this.chessGameDao = chessGameDao;
        this.moveDao = moveDao;
    }

    public Long save(GameStatus gameStatus) {
        if (getRecentPlayableGame().isPresent()) {
            return getRecentPlayableGame().get().id();
        }

        ChessGameRequest chessGameRequest = new ChessGameRequest(gameStatus.name());
        return chessGameDao.save(chessGameRequest);
    }

    public List<Move> getRecentPlayableGameMoves() {
        return getRecentPlayableGame()
                .map(ChessGameResponse::id)
                .map(this::getMovesByChessGameId)
                .orElse(Collections.emptyList());
    }

    private Optional<ChessGameResponse> getRecentPlayableGame() {
        return chessGameDao.findRecentPlayableGame();
    }

    private List<Move> getMovesByChessGameId(long chessGameId) {
        return moveDao.findAll(chessGameId).stream()
                .map(MoveResponse::from)
                .toList();
    }

    public void saveMoveHistory(List<Move> moveHistory, Long chessGameId) {
        moveHistory.forEach(move -> moveDao.save(
                new MoveRequest(move.source().getValue(), move.target().getValue(), chessGameId)));
    }

    public void updateGameFinished() {
        chessGameDao.updateGameFinished();
    }
}
