package chess.domain.chessboard;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceStartingPosition;
import chess.domain.chesspiece.ChessPieceType;
import chess.domain.chesspiece.movestrategy.EmptyMoveStrategy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class BoardGenerator {

    private static final BoardGenerator INSTANCE = new BoardGenerator();
    private static final Map<Square, ChessPiece> CHESS_BOARD_CACHE = createBoard();

    private BoardGenerator() {
    }

    public static BoardGenerator getInstance() {
        return INSTANCE;
    }

    public Map<Square, ChessPiece> generate() {
        return new LinkedHashMap<>(CHESS_BOARD_CACHE);
    }

    private static Map<Square, ChessPiece> createBoard() {
        List<Square> squares = createSquares();
        Map<Square, ChessPiece> chessBoard = new LinkedHashMap<>();
        putChessPieceOnBoard(squares, chessBoard);
        return chessBoard;
    }

    private static List<Square> createSquares() {
        return Arrays.stream(Lettering.values())
                .flatMap(lettering -> Arrays.stream(Numbering.values())
                        .map(numbering -> new Square(lettering, numbering)))
                .toList();
    }

    private static void putChessPieceOnBoard(List<Square> squares, Map<Square, ChessPiece> chessBoard) {
        for (Square square : squares) {
            ChessPiece chessPiece = createChessPieceForSquare(square);
            chessBoard.put(square, chessPiece);
        }
    }

    private static ChessPiece createChessPieceForSquare(Square square) {
        Camp camp = ChessPiece.determineCamp(square.getNumbering());
        ChessPieceType chessPieceType = determineChessPieceType(square);
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(chessPieceType, new EmptyMoveStrategy());
        return ChessPiece.of(camp, chessPieceProperty);
    }

    private static ChessPieceType determineChessPieceType(Square square) {
        ChessPieceStartingPosition chessPieceStartingPosition = ChessPieceStartingPosition.getInstance();
        return chessPieceStartingPosition.determineChessPieceType(square);
    }
}
