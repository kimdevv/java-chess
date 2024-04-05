package state.chessGame.statusfactory;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Map;
import state.chessGame.ChessGameStateEnd;
import state.chessGame.ChessGameStateKingCaught;
import state.chessGame.ChessGameStateRunning;
import state.chessGame.InitialChessGameState;
import state.chessGame.base.ChessGameState;

public class ChessStatusFactory {

    public static ChessGameState initChessGame() {
        return new InitialChessGameState();
    }

    public static ChessGameState makeRunningChessGame(Long gameId, Map<Coordinate, ChessPiece> board, String color) {
        return new ChessGameStateRunning(new ChessBoard(board), gameId, color);
    }

    public static ChessGameState makeEndChessGame(Long gameId) {
        return new ChessGameStateEnd(gameId);
    }

    public static ChessGameState makeKingCaughtChessGame(ChessBoard chessBoard, Color color, Long gameId) {
        return new ChessGameStateKingCaught(chessBoard, color, gameId);
    }
}
