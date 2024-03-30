package chess.service;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.generator.ChessSpaceGenerator;
import chess.repository.ChessBoardRepository;

public class ChessBoardService {

    private final ChessBoardRepository chessBoardRepository;

    public ChessBoardService(ChessBoardRepository chessBoardRepository) {
        this.chessBoardRepository = chessBoardRepository;
    }

    public ChessBoard createChessBoard() {
        ChessBoard chessBoard = ChessBoard.create(new ChessSpaceGenerator());
        return chessBoardRepository.createChessBoard(chessBoard);
    }

    public ChessBoard findChessBoard() {
        return chessBoardRepository.findChessBoard()
                .orElseThrow(() -> new IllegalArgumentException("저장된 데이터가 없습니다"));
    }

    public ChessBoard saveChessBoard(ChessBoard chessBoard) {
        return chessBoardRepository.saveChessBoard(chessBoard);
    }

    public void deleteChessBoard() {
        chessBoardRepository.deleteChessBoard();
    }
}
