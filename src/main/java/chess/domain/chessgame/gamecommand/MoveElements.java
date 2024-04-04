package chess.domain.chessgame.gamecommand;

import chess.domain.chessboard.Square;

public class MoveElements {

    private final String moveSource;
    private final String target;

    public MoveElements(String moveSource, String target) {
        this.moveSource = moveSource;
        this.target = target;
    }

    public Square createMoveSourceSquare() {
        return new Square(moveSource);
    }

    public Square createTargetSquare() {
        return new Square(target);
    }
}
