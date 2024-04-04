package chess.domain.chessboard;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceType;
import chess.domain.chesspiece.movestrategy.EmptyMoveStrategy;
import java.util.Map;

public class ChessBoard {

    private static final BoardGenerator BOARD_GENERATOR = BoardGenerator.getInstance();
    private static final int KING_TYPE_COUNT = 2;

    private final Map<Square, ChessPiece> board;

    private ChessBoard(Map<Square, ChessPiece> board) {
        this.board = board;
    }

    public static ChessBoard initialBoard() {
        return new ChessBoard(BOARD_GENERATOR.generate());
    }

    public static ChessBoard from(Map<Square, ChessPiece> chessBoard) {
        return new ChessBoard(chessBoard);
    }

    public void move(Square startSquare, Square targetSquare) {
        ChessPiece chessPiece = findChessPieceOnSquare(startSquare);
        ChessPieceProperty chessPieceProperty = chessPiece.getChessPieceProperty();
        chessPieceProperty.executeMoveStrategy(this, startSquare, targetSquare);
    }

    public ChessPiece findChessPieceOnSquare(Square findSquare) {
        return board.get(findSquare);
    }

    public Square findForwardSquare(Square square) {
        Numbering numbering = square.getNumbering();
        Numbering nextNumbering = numbering.findNextNumbering();
        return new Square(square.getLettering(), nextNumbering);
    }

    public Square findBackwardSquare(Square square) {
        Numbering numbering = square.getNumbering();
        Numbering previousNumbering = numbering.findPreviousNumbering();
        return new Square(square.getLettering(), previousNumbering);
    }

    public Square findLeftSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering leftLettering = lettering.findPreviousLettering();
        return new Square(leftLettering, square.getNumbering());
    }

    public Square findRightSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering rightLettering = lettering.findNextLettering();
        return new Square(rightLettering, square.getNumbering());
    }

    public Square findLeftForwardDiagonalSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering leftLettering = lettering.findPreviousLettering();
        Numbering numbering = square.getNumbering();
        Numbering nextNumbering = numbering.findNextNumbering();
        return new Square(leftLettering, nextNumbering);
    }

    public Square findRightForwardDiagonalSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering rightLettering = lettering.findNextLettering();
        Numbering numbering = square.getNumbering();
        Numbering nextNumbering = numbering.findNextNumbering();
        return new Square(rightLettering, nextNumbering);
    }

    public Square findLeftBackwardDiagonalSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering leftLettering = lettering.findPreviousLettering();
        Numbering numbering = square.getNumbering();
        Numbering previousNumbering = numbering.findPreviousNumbering();
        return new Square(leftLettering, previousNumbering);
    }

    public Square findRightBackwardDiagonalSquare(Square square) {
        Lettering lettering = square.getLettering();
        Lettering rightLettering = lettering.findNextLettering();
        Numbering numbering = square.getNumbering();
        Numbering previousNumbering = numbering.findPreviousNumbering();
        return new Square(rightLettering, previousNumbering);
    }

    public void movePiece(Square moveSource, Square target) {
        ChessPiece moveSourceChessPiece = board.get(moveSource);
        ChessPiece emptyChessPiece = ChessPiece.of(Camp.NONE,
                new ChessPieceProperty(ChessPieceType.NONE, new EmptyMoveStrategy()));
        board.computeIfPresent(moveSource, (k, v) -> emptyChessPiece);
        board.computeIfPresent(target, (k, v) -> moveSourceChessPiece);
    }

    public boolean isKingDead() {
        return board.values()
                .stream()
                .filter(ChessPiece::isChessPieceKing).count() < KING_TYPE_COUNT;
    }

    public Camp campKingAlive() {
        return board.values()
                .stream()
                .filter(ChessPiece::isChessPieceKing)
                .map(ChessPiece::getCamp)
                .findFirst()
                .orElseThrow();
    }

    public Map<Square, ChessPiece> getBoard() {
        return board;
    }
}
