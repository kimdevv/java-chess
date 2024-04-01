package chess.dao.convertor;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.piece.Piece;

public class PiecesConvertor {

    public static Piece mapToDomain(PieceDto pieceDto) {
        Color color = Color.of(pieceDto.team());
        String pieceType = pieceDto.type();
        Square square = Square.of(File.of(pieceDto.file()), Rank.of(pieceDto.rank()));
        return PieceConvertor.convertTo(color, pieceType, square);
    }
}
