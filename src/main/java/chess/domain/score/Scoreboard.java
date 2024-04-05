package chess.domain.score;

import java.util.Arrays;
import java.util.List;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.File;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;

public class Scoreboard {

    private static final long OVERLAPPED_PAWN_COUNT_THRESHOLD = 2;

    private final Chessboard chessboard;

    public Scoreboard(final Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public double getScoreByColor(final Color color) {
        return sumScoresExceptPawn(color) + sumPawnScores(color);
    }

    private double sumScoresExceptPawn(final Color color) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> sumScoreExceptPawn(chessboard.getAllFrom(file), color))
                .sum();
    }

    private double sumScoreExceptPawn(final List<Piece> pieces, final Color color) {
        return pieces.stream()
                .filter(piece -> !piece.isPawn())
                .filter(piece -> piece.color() == color)
                .mapToDouble(Piece::score)
                .sum();
    }

    private double sumPawnScores(final Color color) {
        return Arrays.stream(File.values())
                .map(chessboard::getAllFrom)
                .mapToDouble(pieces -> sumPawnScore(pieces, color))
                .sum();
    }

    private double sumPawnScore(final List<Piece> piecesInFile, final Color color) {
        final boolean isOverlapped = isPawnOverlapped(piecesInFile);
        return piecesInFile.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.color() == color)
                .mapToDouble(piece -> piece.score(isOverlapped))
                .sum();
    }

    private boolean isPawnOverlapped(final List<Piece> piecesInFile) {
        return piecesInFile.stream()
                .filter(Pawn.class::isInstance)
                .count() >= OVERLAPPED_PAWN_COUNT_THRESHOLD;
    }
}
