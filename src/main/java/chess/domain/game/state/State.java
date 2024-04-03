package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public interface State {

    State start();

    State finish();

    boolean isEnd();

    State move(Board board, Point departure, Point destination);

    double calculateScore(Board board, Team team);

    Team getTeam();
}
