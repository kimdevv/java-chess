package service;

import dao.GameDao;
import dao.PieceDao;

public class TestableDBService extends DBService {
    public TestableDBService(GameDao gameDao, PieceDao pieceDao) {
        super(gameDao, pieceDao);
    }
}
