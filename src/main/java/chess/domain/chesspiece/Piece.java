package chess.domain.chesspiece;

import static chess.domain.GameStatus.BLACK_TURN;
import static chess.domain.GameStatus.WHITE_TURN;
import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.GameStatus;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Team team;
    private final Score score;

    public Piece(Team team, Score score) {
        this.team = team;
        this.score = score;
    }

    public abstract List<Position> findRoute(Position source, Position target, boolean isEmpty);

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract boolean isPawn();

    public abstract boolean isEmpty();

    public abstract Score calculateScore(Score score, boolean hasSameFilePawn);

    public void checkValidMove(GameStatus turn) {
        if (turn == WHITE_TURN && team == BLACK) {
            throw new IllegalArgumentException("백팀이 움직일 차례입니다.");
        }
        if (turn == BLACK_TURN && team == WHITE) {
            throw new IllegalArgumentException("흑팀이 움직일 차례입니다.");
        }
    }

    public boolean isTeam(Piece piece) {
        return team == piece.team;
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public boolean isKing() {
        return false;
    }

    public Team getTeam() {
        return team;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
