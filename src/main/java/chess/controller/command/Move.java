package chess.controller.command;

import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.service.GameService;
import chess.view.OutputView;

public class Move implements Command {

    private final Position source;
    private final Position target;

    public Move(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Game execute(GameService gameService, OutputView outputView) {
        throw new IllegalArgumentException("사용할 수 없는 명령어입니다.");
    }

    @Override
    public void execute(Game game, GameService gameService, OutputView outputView) {
        game.proceedTurn(source, target);
        outputView.printBoard(game.board());
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
