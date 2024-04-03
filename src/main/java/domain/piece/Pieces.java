package domain.piece;

import domain.Square;
import domain.Team;

import java.util.Collections;
import java.util.Map;

public class Pieces {
    final Map<Square, Piece> pieces;

    public Pieces(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square source, final Square target) {
        pieces.put(target, get(source));
        pieces.remove(source);
    }

    public double score(final Team team) {
        return sumScores(team);
    }

    private double sumScores(final Team team) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().team() == team)
                .mapToDouble(entry -> entry.getValue().getScore(pieces, entry.getKey()))
                .sum();
    }

    public boolean hasPiece(final Square source) {
        return !pieces.containsKey(source);
    }

    public boolean cannotMove(final Square source, final Square target) {
        return get(source).cannotMove(source, target, pieces);
    }

    public boolean isOppositeTeam(final Square source, final Team team) {
        return get(source).isOppositeTeam(team);
    }

    public boolean isKingCountUnder(final int count) {
        return getKingCount() < count;
    }

    private int getKingCount() {
        return (int) pieces.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    private Piece get(final Square source) {
        return pieces.get(source);
    }
}
