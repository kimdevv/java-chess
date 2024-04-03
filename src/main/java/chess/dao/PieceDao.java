package chess.dao;

import chess.dto.PieceDto;

import java.util.List;

public interface PieceDao {

    void save(final PieceDto piece);

    PieceDto findOne(final String file, final String rank);

    List<PieceDto> findAll();

    void deleteAll();

    boolean hasRecords();
}
