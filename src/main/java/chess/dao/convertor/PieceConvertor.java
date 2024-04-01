package chess.dao.convertor;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Arrays;

public enum PieceConvertor {
    KING() {
        @Override
        Piece convert(Color color, Square square) {
            return new King(color, square);
        }
    },
    QUEEN {
        @Override
        Piece convert(Color color, Square square) {
            return new Queen(color, square);
        }
    },
    ROOK {
        @Override
        Piece convert(Color color, Square square) {
            return new Rook(color, square);
        }
    },
    BISHOP {
        @Override
        Piece convert(Color color, Square square) {
            return new Bishop(color, square);
        }
    },
    KNIGHT {
        @Override
        Piece convert(Color color, Square square) {
            return new Knight(color, square);
        }
    },
    PAWN {
        @Override
        Piece convert(Color color, Square square) {
            if (color.equals(Color.WHITE)) {
                return new WhitePawn(square);
            }
            return new BlackPawn(square);
        }
    };

    abstract Piece convert(Color color, Square square);

    public static Piece convertTo(Color color, String pieceType, Square square) {
        return Arrays.stream(values())
                .filter(pieceConvertor -> pieceConvertor.name().equals(pieceType))
                .map(pieceConvertor -> pieceConvertor.convert(color, square))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("%s라는 기물의 타입이 존재하지 않습니다.".formatted(pieceType)));
    }
}
