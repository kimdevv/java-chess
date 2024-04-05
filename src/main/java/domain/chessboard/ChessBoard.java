package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.pawn.Pawn;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Coordinate, ChessPiece> board;

    public ChessBoard(Map<Coordinate, ChessPiece> chessBoard) {
        board = chessBoard;
    }

    public void playTurn(Coordinate start, Coordinate destination) {
        ChessPiece piece = findPiece(start);
        checkPath(start, destination, piece);

        board.replace(start, new Blank());
        board.replace(destination, piece);
    }

    private void checkPath(Coordinate start, Coordinate destination, ChessPiece piece) {
        Direction direction = piece.getDirection(start, destination, !board.get(destination).isBlank());

        validateNoPieceOnPath(start, destination, direction);

        if (board.get(destination).isBlank() && piece.isPawn()) {
            validateCanMoveTwoDistance(start, start.calculateRowDifference(destination), (Pawn) piece);
        }
    }

    private void validateNoPieceOnPath(Coordinate coordinate, Coordinate destination, Direction direction) {
        while (!coordinate.equals(destination)) {
            coordinate = coordinate.next(direction);
            hasPieceOnPath(coordinate, destination);
        }
    }

    private void hasPieceOnPath(Coordinate coordinate, Coordinate destination) {
        if (coordinate.equals(destination)) {
            return;
        }
        if (!findPiece(coordinate).isBlank()) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private void validateCanMoveTwoDistance(Coordinate coordinate, int rowDifference, Pawn pawn) {
        if (!pawn.isFirstPosition(coordinate) && Math.abs(rowDifference) == 2) {
            throw new IllegalArgumentException("2칸을 이동할 수 있는 상태가 아닙니다.");
        }
    }

    private ChessPiece findPiece(Coordinate coordinate) {
        return board.get(coordinate);
    }

    public boolean isOpponentColorKing(Coordinate destination, Color currentTurn) {
        ChessPiece destinationPiece = board.get(destination);
        if (destinationPiece.isOpponentColor(currentTurn)) {
            return destinationPiece.isKing();
        }
        return false;
    }

    public boolean isCanNotMoveDestination(Coordinate destination, Color color) {
        ChessPiece chessPiece = findPiece(destination);
        if (chessPiece.isBlank()) {
            return false;
        }

        return chessPiece.isSameColor(color);
    }

    public boolean isCanNotMovePiece(Coordinate coordinate, Color color) {
        ChessPiece chessPiece = findPiece(coordinate);
        if (chessPiece.isBlank()) {
            return true;
        }

        return chessPiece.isOpponentColor(color);
    }

    public Map<Coordinate, ChessPiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
