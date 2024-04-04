package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;
import java.util.Map;

public class ChessBoard {

    public static final int SIZE = 8;
    private final Map<Coordinate, ChessPiece> board;

    public ChessBoard(Map<Coordinate, ChessPiece> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(ChessBoardInitializer.createInitialBoard());
    }

    public MovingResult movePiece(Coordinate start, Coordinate destination, Color currentTurnColor) {
        Direction direction = getMovingPiece(start, currentTurnColor).getDirection(start, destination);
        validateDestination(destination, currentTurnColor);
        validatePath(direction, start, destination);

        return move(start, destination);
    }

    private ChessPiece getMovingPiece(Coordinate start, Color currentTurnColor) {
        ChessPiece piece = getPiece(start);

        if (!isEmptyCoordinate(start) && !piece.hasSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("색상이 다른 말을 움직일 수 없습니다.");
        }
        return piece;
    }

    private void validateDestination(Coordinate destination, Color currentTurnColor) {
        if (isEmptyCoordinate(destination)) {
            return;
        }
        if (getPiece(destination).hasSameColor(currentTurnColor)) {
            throw new IllegalArgumentException("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    private void validatePath(Direction direction, Coordinate start, Coordinate destination) {
        Coordinate current = start.moveOneStep(direction);

        while (!current.equals(destination)) {
            validatePieceExist(current);
            current = current.moveOneStep(direction);
        }
    }

    private void validatePieceExist(Coordinate current) {
        if (!isEmptyCoordinate(current)) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private boolean isEmptyCoordinate(Coordinate coordinate) {
        ChessPiece piece = getPiece(coordinate);
        return piece.isSameType(PieceType.BLANK);
    }

    private MovingResult move(Coordinate start, Coordinate destination) {
        ChessPiece movingPiece = board.get(start);
        ChessPiece removedPiece = board.get(destination);

        board.put(start, Blank.getInstance());
        board.put(destination, movingPiece);

        return new MovingResult(movingPiece, removedPiece);
    }

    private ChessPiece getPiece(Coordinate coordinate) {
        ChessPiece piece = board.get(coordinate);
        if (piece == null) {
            throw new IllegalArgumentException("올바른 위치가 아닙니다.");
        }
        return piece;
    }

    public Map<Coordinate, ChessPiece> getBoard() {
        return Map.copyOf(board);
    }
}
