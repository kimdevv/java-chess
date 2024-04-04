package chess.dto;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import java.util.Map;

public record ChessBoardDto(
        Map<Square, ChessPiece> chessBoard) {

    public ChessBoardDto(ChessBoard chessBoard) {
        this(chessBoard.getBoard());
    }
}
