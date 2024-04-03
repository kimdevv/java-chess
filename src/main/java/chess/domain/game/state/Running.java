package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public class Running implements State {
    private final Team team;

    public Running(final Team team) {
        this.team = team;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("이미 게임이 시작되었습니다.");
    }

    @Override
    public State finish() {
        return new End(team.opposite());
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State move(final Board board, final Point departure, final Point destination) {
        final Piece destinationPiece = board.findPieceByPoint(destination);
        board.move(team, departure, destination);

        if (destinationPiece.isKing()) {
            return new End(team.opposite());
        }
        return new Running(team.opposite());
    }

    @Override
    public double calculateScore(final Board board, final Team team) {
        return board.calculateTotalScore(team);
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
