package chess.domain.scorerule;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {
    private final Map<Position, Piece> piecePositions;

    public Referee(final Map<Position, Piece> piecePositions) {
        this.piecePositions = piecePositions;
    }

    public double calculateScore(final Color color) {
        Map<Position, Piece> teamPieces = getTeamPieces(color);
        Map<PieceType, List<Position>> positionsByPieceType = getPositionsByPieceType(teamPieces);

        return positionsByPieceType.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().findScore(entry.getValue()))
                .sum();
    }

    private Map<Position, Piece> getTeamPieces(final Color color) {
        return piecePositions.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeamColor(color))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<PieceType, List<Position>> getPositionsByPieceType(final Map<Position, Piece> teamPieces) {
        return teamPieces.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getValue().getPieceType(),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    }
}
