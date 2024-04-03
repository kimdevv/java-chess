package domain;

import domain.piece.Piece;
import domain.piece.Pieces;

import java.util.Map;

public class ChessBoard {
    public static final int KING_COUNT_THRESHOLD = 2;

    private final Pieces pieces;
    private Team team;
    
    public ChessBoard(final Map<Square, Piece> pieces, final Team team) {
        this.pieces = new Pieces(pieces);
        this.team = team;
    }

    public ChessBoard(final Map<Square, Piece> pieces) {
        this.pieces = new Pieces(pieces);
        this.team = Team.WHITE;
    }

    public void move(final Square source, final Square target) {
        validateEmptySource(source);
        validateSameSquare(source, target);
        validateTeam(source);
        validateMove(source, target);

        pieces.move(source, target);
        team = team.turn();
    }

    private void validateSameSquare(final Square source, final Square target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("제자리 이동은 불가능합니다.");
        }
    }

    private void validateEmptySource(final Square source) {
        if (pieces.hasPiece(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateTeam(final Square source) {
        if (pieces.isOppositeTeam(source, team)) {
            throw new IllegalArgumentException("자기 말이 아닙니다.");
        }
    }

    private void validateMove(final Square source, final Square target) {
        if (pieces.cannotMove(source, target)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
    }

    public Map<Team, Double> status() {
        final double whiteScore = pieces.score(Team.WHITE);
        final double blackScore = pieces.score(Team.BLACK);
        return Map.of(Team.WHITE, whiteScore, Team.BLACK, blackScore);
    }

    public Map<Square, Piece> getPieces() {
        return pieces.getPieces();
    }

    public boolean isFinished() {
        return pieces.isKingCountUnder(KING_COUNT_THRESHOLD);
    }

    public Team currentTeam() {
        return team;
    }
}
