package chess.view;

import chess.domain.game.Score;
import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printCommandGuide() {
        System.out.println(messageResolver.resolveCommandGuide());
    }

    public void printBoard(List<Piece> pieces) {
        System.out.println(messageResolver.resolveBoardMessage(pieces));
    }

    public void printScore(Score score) {
        System.out.println(messageResolver.resolveScoreMessage(score));
    }

    public void printSaveCommandGuide() {
        System.out.println(messageResolver.resolveSaveCommandGuideMessage());
    }
}
