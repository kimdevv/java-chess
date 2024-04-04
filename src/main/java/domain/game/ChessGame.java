package domain.game;

import domain.chessboard.ChessBoard;
import domain.chessboard.MovingResult;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Map;

public class ChessGame {

    private static final ChessScore chessScore = new ChessScore();
    private static final Color DEFAULT_START_COLOR = Color.WHITE;

    private final ChessBoard chessBoard;

    private Color movablePieceColor;

    public ChessGame(ChessBoard chessBoard, Color movablePieceColor) {
        this.chessBoard = chessBoard;
        this.movablePieceColor = movablePieceColor;
    }

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        movablePieceColor = DEFAULT_START_COLOR;
    }

    public MovingResult startTurn(Coordinate start, Coordinate destination) {
        MovingResult movingResult = chessBoard.movePiece(start, destination, movablePieceColor);
        movablePieceColor = movablePieceColor.getOpponentColor();

        return movingResult;
    }

    public double calculatePlayerScore() {
        return chessScore.getScore(getCurrentBoard(), movablePieceColor);
    }

    public double calculateOpponentScore() {
        return chessScore.getScore(getCurrentBoard(), movablePieceColor.getOpponentColor());
    }

    public Map<Coordinate, ChessPiece> getCurrentBoard() {
        return chessBoard.getBoard();
    }

    public Color getMovablePieceColor() {
        return movablePieceColor;
    }
}
