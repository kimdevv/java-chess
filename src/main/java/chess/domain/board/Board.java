package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.Type;
import chess.domain.point.File;
import chess.domain.point.Point;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final double NUMBER_TO_MAKE_PENALTY_SCORE = 0.5;
    private static final int MINIMUM_NUMBER_OF_PENALTY = 2;
    private static final int INITIAL_KING_COUNT = 2;

    private final Map<Point, Piece> board;

    public Board(final Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(final Team team, final Point departure, final Point destination) {
        final Piece piece = board.get(departure);

        validateMove(team, departure, destination, piece);

        movePiece(departure, destination, piece);
    }

    private void movePiece(final Point departure, final Point destination, final Piece piece) {
        board.put(departure, Empty.INSTANCE);
        board.put(destination, piece);
    }

    private void validateMove(final Team team, final Point departure, final Point destination, final Piece piece) {
        validateMyPiece(team, piece);
        validateMovePoint(departure, destination, piece);
    }

    private void validateMyPiece(final Team team, final Piece piece) {
        if (!piece.isTeamMatch(team)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovePoint(final Point departure, final Point destination, final Piece piece) {
        if (!piece.canMove(departure, destination, board)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    public Piece findPieceByPoint(final Point point) {
        return board.get(point);
    }

    public double calculateTotalScore(final Team team) {
        final double score = calculateScore(team);
        final double penalty = calculatePenalty(team);

        return score - penalty;
    }

    private double calculateScore(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isTeamMatch(team))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePenalty(final Team team) {
        final Map<File, Long> pawnCountInSameLine = countPawnInSameLine(team);

        return pawnCountInSameLine.values()
                .stream()
                .filter(count -> count >= MINIMUM_NUMBER_OF_PENALTY)
                .mapToDouble(count -> count * NUMBER_TO_MAKE_PENALTY_SCORE)
                .sum();
    }

    private Map<File, Long> countPawnInSameLine(final Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> isSameTeamPawn(team, entry.getValue()))
                .map(entry -> entry.getKey().getFile())
                .collect(Collectors.groupingBy(file -> file, Collectors.counting()));
    }

    private boolean isSameTeamPawn(final Team team, final Piece piece) {
        return piece.isPawn() && piece.isTeamMatch(team);
    }

    public boolean isKingDead() {
        return board.values().stream()
                .filter(piece -> piece.getType() == Type.KING)
                .count() < INITIAL_KING_COUNT;
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
