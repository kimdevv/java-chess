package dao;

import dto.PieceDto;
import java.sql.Connection;
import java.util.List;

public interface PieceDao {
    void addAll(Connection connection, List<PieceDto> pieceDtos, int gameId);

    List<PieceDto> findAllPieces(Connection connection, int gameId);
}
