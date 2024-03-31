package chess.db;

import chess.dto.PieceDto;
import java.util.List;

public interface PiecesDao {

    void create(PieceDto pieceDto);

    List<PieceDto> findAll();

    void deleteAll();

    int count();
}
