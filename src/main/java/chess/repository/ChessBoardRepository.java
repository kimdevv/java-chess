package chess.repository;

import chess.domain.chessGame.ChessBoard;
import java.util.Optional;

public interface ChessBoardRepository {

    ChessBoard createChessBoard(ChessBoard chessBoard);

    Optional<ChessBoard> findChessBoard();

    ChessBoard saveChessBoard(ChessBoard chessBoard);

    void deleteChessBoard();
}
