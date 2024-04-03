package chess.repository.mapper;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.entity.PieceEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardMapper {

    public static ChessBoard mapToBoard(List<PieceEntity> pieceEntities) {
        Map<Position, Piece> positionPiece = new HashMap<>();

        for (PieceEntity pieceEntity : pieceEntities) {
            Position position = PositionMapper.mapToPosition(pieceEntity.getPosition());
            Piece piece = PieceMapper.mapValueToPiece(pieceEntity.getType(), pieceEntity.getTeam());
            positionPiece.put(position, piece);
        }

        return new ChessBoard(positionPiece);
    }

    public static List<PieceEntity> mapToEntities(ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getBoard();
        return board.keySet().stream()
                .map(position -> PieceEntity.of(chessBoard.findPieceByPosition(position), position))
                .toList();
    }
}
