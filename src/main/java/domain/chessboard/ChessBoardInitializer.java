package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardInitializer {

    private ChessBoardInitializer() {
    }

    public static Map<Coordinate, ChessPiece> createInitialBoard() {
        Map<Coordinate, ChessPiece> board = new HashMap<>();
        for (int column = 0; column < ChessBoard.SIZE; column++) {
            board.put(new Coordinate(0, column), createFirstRank(Color.BLACK).get(column));
            board.put(new Coordinate(1, column), new BlackPawn());
            board.put(new Coordinate(2, column), Blank.getInstance());
            board.put(new Coordinate(3, column), Blank.getInstance());
            board.put(new Coordinate(4, column), Blank.getInstance());
            board.put(new Coordinate(5, column), Blank.getInstance());
            board.put(new Coordinate(6, column), new WhitePawn());
            board.put(new Coordinate(7, column), createFirstRank(Color.WHITE).get(column));
        }
        return board;
    }

    private static List<ChessPiece> createFirstRank(Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }
}
