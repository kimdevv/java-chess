package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.status.BlackTurn;
import chess.domain.game.status.GameStatus;
import chess.domain.game.status.WhiteTurn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Game {
    private GameStatus gameStatus;

    public Game(final BoardInitializer boardInitializer) {
        this.gameStatus = new BlackTurn(new Board(boardInitializer));
    }

    public Game(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static Game of(final BoardInitializer boardInitializer, final Color turnColor) {
        return new Game(findGameStatus(boardInitializer, turnColor));
    }

    private static GameStatus findGameStatus(final BoardInitializer boardInitializer, final Color turnColor) {
        if (turnColor.isSameColor(Color.BLACK)) {
            return new BlackTurn(new Board(boardInitializer));
        }

        return new WhiteTurn(new Board(boardInitializer));
    }

    public void move(final Position source, final Position target) {
        gameStatus = gameStatus.move(source, target);
    }

    public boolean isFinish() {
        return gameStatus.isFinish();
    }

    public Map<Position, Piece> getBoard() {
        return gameStatus.getBoard().getBoard();
    }

    public Color getTurn() {
        return gameStatus.getTurn();
    }
}
