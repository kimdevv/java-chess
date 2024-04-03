package chess.service;

import chess.dao.MoveDao;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.position.Move;
import chess.domain.position.Position;
import chess.dto.MoveRequest;
import chess.dto.MoveResponse;
import java.util.List;

public class ChessGameService {
    private final MoveDao moveDao;

    public ChessGameService(MoveDao moveDao) {
        this.moveDao = moveDao;
    }

    public ChessGame createInitialChessGame() {
        return new ChessGame(BoardFactory.createInitialBoard());
    }

    public void addMoveHistory(Position source, Position target) {
        MoveRequest moveRequest = MoveRequest.of(source, target);
        moveDao.save(moveRequest);
    }

    public List<Move> getMoveHistories() {
        return moveDao.findAll().stream()
                .map(MoveResponse::from)
                .toList();
    }

    public void deleteAllMoves() {
        moveDao.deleteAll();
    }
}
