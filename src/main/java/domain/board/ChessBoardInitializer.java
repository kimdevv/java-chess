package domain.board;

import domain.Side;
import domain.piece.*;
import domain.position.InitPosition;
import domain.position.Position;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardInitializer {

    public static Map<Position, Piece> init() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        return initChessBoard(board);
    }

    private static Map<Position, Piece> initChessBoard(Map<Position, Piece> board) {
        Arrays.stream(Side.values())
                .filter(Side::isNotNone)
                .forEach(side -> initSide(side, board));
        return board;
    }

    private static void initSide(Side side, Map<Position, Piece> board) {
        initPiece(InitPosition.ROOK, side, new Rook(side), board);
        initPiece(InitPosition.KNIGHT, side, new Knight(side), board);
        initPiece(InitPosition.BISHOP, side, new Bishop(side), board);
        initPiece(InitPosition.QUEEN, side, new Queen(side), board);
        initPiece(InitPosition.KING, side, new King(side), board);
        initPiece(InitPosition.PAWN, side, new Pawn(side), board);
    }

    private static void initPiece(InitPosition initPosition, Side side, Piece piece, Map<Position, Piece> board) {
        List<File> files = initPosition.files();
        Rank rank = initPosition.rank(side);
        files.forEach(file -> board.put(Position.valueOf(file, rank), piece));
    }
}
