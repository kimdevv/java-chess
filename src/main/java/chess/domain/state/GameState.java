package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.game.Color;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState move(Board board, Position source, Position target);

    GameState end();

    GameState status();

    boolean isPlaying();

    Color getCurrentColor();
}
