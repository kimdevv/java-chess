package chess.service;

import chess.dao.ChessDAO;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessDTO;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService(final ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
    }

    public ChessDTO loadChess() {
        Map<Position, Piece> board = generateEmptyBoard();
        try {
            board.putAll(chessDAO.getBoard());
            return new ChessDTO(new Board(board), chessDAO.getCurrentTurnColor());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Map<Position, Piece> generateEmptyBoard() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file, rank)))
                .collect(Collectors.toMap(position -> position, position -> Piece.EMPTY_PIECE));
    }

    public void saveChess(final Board board, final Color color) {
        chessDAO.updateBoard(board);
        chessDAO.updateColor(color);
    }

    public void initializeChess() {
        chessDAO.initialize();
    }
}
