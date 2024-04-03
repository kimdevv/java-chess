package chess.dao;

import chess.dto.PieceDto;
import chess.dto.TurnDto;

import java.util.List;

public class ChessDaoService {
    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public ChessDaoService(final PieceDao pieceDao, final TurnDao turnDao) {
        this.pieceDao = pieceDao;
        this.turnDao = turnDao;
    }

    public boolean isPreviousDataExist() {
        return pieceDao.hasRecords();
    }

    public List<PieceDto> loadPreviousData() {
        return pieceDao.findAll();
    }

    public TurnDto loadPreviousTurn() {
        return turnDao.findOne();
    }

    public void updatePiece(final List<PieceDto> pieceDtos) {
        pieceDao.deleteAll();
        for (PieceDto pieceDto : pieceDtos) {
            pieceDao.save(pieceDto);
        }
    }

    public void updateTurn(final TurnDto turnDto) {
        turnDao.deleteAll();
        turnDao.update(turnDto);
    }

    public void deletePreviousData() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }
}
