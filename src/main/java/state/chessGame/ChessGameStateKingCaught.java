package state.chessGame;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Map;
import state.chessGame.base.NotRunningGameState;
import view.OutputView;

public class ChessGameStateKingCaught extends NotRunningGameState {

    private final ChessBoard chessBoard;
    private final Color currentTurn;
    private final Long gameId;

    public ChessGameStateKingCaught(ChessBoard chessBoard, Color currentTurn, Long gameId) {
        this.chessBoard = chessBoard;
        this.currentTurn = currentTurn;
        this.gameId = gameId;
    }

    @Override
    public Map<Coordinate, ChessPiece> getBoard() {
        return chessBoard.getBoard();
    }

    @Override
    public boolean isKingCaught() {
        return true;
    }

    @Override
    public Long getGameId() {
        return gameId;
    }

    @Override
    public void show() {
        OutputView.printResult(currentTurn);
    }
}
