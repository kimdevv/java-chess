package chess.domain.board;

import static chess.domain.pieceinfo.PieceScore.PAWN_LOWER_SCORE;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.Team;
import java.util.List;

public class LivePiecesInFile {
    private final List<Piece> livePieces;

    public LivePiecesInFile(List<Piece> livePieces) {
        this.livePieces = livePieces;
    }

    public double calculatePieceScoreSum(Team team) {
        double scoreSum = livePieces.stream()
                .filter(piece -> piece.getTeam() == team)
                .map(Piece::getScore)
                .reduce(0.0, Double::sum);
        double pawnCount = getPawnCount(team);

        if (pawnCount == 1) {
            return scoreSum;
        }
        return scoreSum - PAWN_LOWER_SCORE.get() * pawnCount;
    }

    private Long getPawnCount(Team team) {
        return livePieces.stream()
                .filter(piece -> PieceType.isPawn(piece.getType()))
                .filter(piece -> piece.getTeam() == team)
                .count();
    }
}
