package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.Square;
import java.util.List;

public class GameElements {

    private final GameCommand gameCommand;
    private final MoveElements moveElements;

    public GameElements(GameCommand gameCommand, MoveElements moveElements) {
        this.gameCommand = gameCommand;
        this.moveElements = moveElements;
    }

    public List<Square> createMoveSquare() {
        Square moveSource = moveElements.createMoveSourceSquare();
        Square target = moveElements.createTargetSquare();
        return List.of(moveSource, target);
    }

    public GameCommandState findGameCommandState() {
        return gameCommand.findGameCommandState();
    }
}
