package state.chessGame;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Map;
import state.chessGame.base.ChessGameState;
import state.chessGame.statusfactory.ChessStatusFactory;
import view.OutputView;

public class ChessGameStateRunning implements ChessGameState {

    private final ChessBoard chessBoard;
    private final Long gameId;

    private Color currentTurn;

    public ChessGameStateRunning(ChessBoard chessBoard, Long gameId, String color) {
        this.chessBoard = chessBoard;
        this.gameId = gameId;
        this.currentTurn = Color.valueOf(color);
    }

    @Override
    public ChessGameState move(Coordinate start, Coordinate destination) {
        validateCanMove(start, destination);
        if (chessBoard.isOpponentColorKing(destination, currentTurn)) {
            chessBoard.playTurn(start, destination);
            return ChessStatusFactory.makeKingCaughtChessGame(chessBoard, currentTurn, gameId);
        }
        chessBoard.playTurn(start, destination);

        currentTurn = currentTurn.changeTurn();
        return this;
    }

    private void validateCanMove(Coordinate start, Coordinate destination) {
        if (chessBoard.isCanNotMovePiece(start, currentTurn) || chessBoard.isCanNotMoveDestination(destination,
                currentTurn)) {
            throw new IllegalArgumentException("움직일 수 없는 말을 선택 했습니다.");
        }
    }

    @Override
    public ChessGameState end() {
        return ChessStatusFactory.makeEndChessGame(gameId);
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public Map<Coordinate, ChessPiece> getBoard() {
        return chessBoard.getBoard();
    }

    @Override
    public boolean isKingCaught() {
        return false;
    }

    @Override
    public Long getGameId() {
        return gameId;
    }

    @Override
    public Color getTurn() {
        return currentTurn;
    }

    @Override
    public void show() {
        OutputView.printBoard(chessBoard.getBoard());
    }
}
