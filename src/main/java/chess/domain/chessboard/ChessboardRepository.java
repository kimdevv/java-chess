package chess.domain.chessboard;

import java.util.Optional;

import chess.fen.Fen;
import chess.fen.dao.FenDao;

public class ChessboardRepository {

    private static final int ID = 1;

    private final FenDao fenDao;

    public ChessboardRepository(final FenDao fenDao) {
        this.fenDao = fenDao;
    }

    public Chessboard save(final Chessboard chessboard) {
        fenDao.save(ID, Fen.from(chessboard));
        return chessboard;
    }

    public Optional<Chessboard> find() {
        if (exists()) {
            return Optional.empty();
        }
        return Optional.of(ChessboardFactory.from(fenDao.find(ID)));
    }

    public void update(final Chessboard chessboard) {
        fenDao.update(ID, Fen.from(chessboard));
    }

    private boolean exists() {
        return fenDao.exists(ID);
    }
}
