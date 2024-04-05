package chess.domain.game;

import chess.domain.board.BoardStatus;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Side;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InitialGameCreator implements GameCreator {

    @Override
    public Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        generateSquares(board);
        initializeSquares(board);
        return board;
    }

    private void generateSquares(Map<Position, Piece> board) {
        Position.allPositions().forEach(
                position -> board.put(position, Empty.instance())
        );
    }

    private void initializeSquares(Map<Position, Piece> board) {
        initializeSide(board, Side.BLACK);
        initializeSide(board, Side.WHITE);
        initializePieces(board, InitialPosition.PAWN.positions(Side.BLACK), new BlackPawn());
        initializePieces(board, InitialPosition.PAWN.positions(Side.WHITE), new WhitePawn());
    }

    private void initializeSide(Map<Position, Piece> board, Side side) {
        initializePieces(board, InitialPosition.ROOK.positions(side), new Rook(side));
        initializePieces(board, InitialPosition.KNIGHT.positions(side), new Knight(side));
        initializePieces(board, InitialPosition.BISHOP.positions(side), new Bishop(side));
        initializePieces(board, InitialPosition.QUEEN.positions(side), new Queen(side));
        initializePieces(board, InitialPosition.KING.positions(side), new King(side));
    }

    private void initializePieces(Map<Position, Piece> board, List<Position> initialPositions, Piece piece) {
        initialPositions.forEach(
                position -> board.put(position, piece)
        );
    }

    @Override
    public BoardStatus createBoardStatus() {
        return BoardStatus.WHITE_TURN;
    }
}
