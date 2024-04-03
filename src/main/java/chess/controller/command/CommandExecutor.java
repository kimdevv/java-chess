package chess.controller.command;

import chess.domain.game.ChessGame;

@FunctionalInterface
public interface CommandExecutor {
    void execute(ChessGame chessGame, CommandCondition commandCondition);
}
