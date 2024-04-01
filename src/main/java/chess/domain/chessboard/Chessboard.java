package chess.domain.chessboard;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.File.A;
import static chess.domain.attribute.File.B;
import static chess.domain.attribute.File.C;
import static chess.domain.attribute.File.D;
import static chess.domain.attribute.File.E;
import static chess.domain.attribute.File.F;
import static chess.domain.attribute.File.G;
import static chess.domain.attribute.File.H;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.TWO;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Score;
import chess.domain.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Chessboard {

    private static final int INITIAL_KING_COUNT = 2;
    private final Map<Square, Piece> chessboard;

    private Chessboard(final Map<Square, Piece> chessboard) {
        this.chessboard = chessboard;
    }

    protected static Chessboard createChessBoard(Map<Square, Piece> map) {
        return new Chessboard(new HashMap<>(map));
    }

    public static Chessboard createChessBoard() {
        Map<Square, Piece> chessboard = new HashMap<>();
        Set<Piece> pieces = new HashSet<>();
        pieces.addAll(createWhitePieces());
        pieces.addAll(createBlackPieces());
        for (Piece piece : pieces) {
            chessboard.put(piece.currentSquare(), piece);
        }
        return new Chessboard(chessboard);
    }

    public static Chessboard of(List<Piece> pieces) {
        Map<Square, Piece> chessboard = new HashMap<>();
        for (Piece piece : pieces) {
            chessboard.put(piece.currentSquare(), piece);
        }
        return new Chessboard(chessboard);
    }

    private static Set<Piece> createWhitePieces() {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(WHITE, Square.of(E, ONE)));
        pieces.add(new Queen(WHITE, Square.of(D, ONE)));
        pieces.add(new Bishop(WHITE, Square.of(F, ONE)));
        pieces.add(new Bishop(WHITE, Square.of(C, ONE)));
        pieces.add(new Knight(WHITE, Square.of(B, ONE)));
        pieces.add(new Knight(WHITE, Square.of(G, ONE)));
        pieces.add(new Rook(WHITE, Square.of(A, ONE)));
        pieces.add(new Rook(WHITE, Square.of(H, ONE)));
        for (File file : File.values()) {
            pieces.add(new WhitePawn(Square.of(file, TWO)));
        }
        return pieces;
    }

    private static Set<Piece> createBlackPieces() {
        Set<Piece> pieces = new HashSet<>();
        pieces.add(new King(BLACK, Square.of(E, EIGHT)));
        pieces.add(new Queen(BLACK, Square.of(D, EIGHT)));
        pieces.add(new Bishop(BLACK, Square.of(F, EIGHT)));
        pieces.add(new Bishop(BLACK, Square.of(C, EIGHT)));
        pieces.add(new Knight(BLACK, Square.of(B, EIGHT)));
        pieces.add(new Knight(BLACK, Square.of(G, EIGHT)));
        pieces.add(new Rook(BLACK, Square.of(A, EIGHT)));
        pieces.add(new Rook(BLACK, Square.of(H, EIGHT)));
        for (File file : File.values()) {
            pieces.add(new BlackPawn(Square.of(file, SEVEN)));
        }
        return pieces;
    }

    public void move(final Square source, final Square target) {
        validateSourceSquareOccupied(source);
        Piece piece = chessboard.get(source);
        Set<Square> movableTargets = new HashSet<>(piece.findLegalMoves(findAllExistPieceOnBoard()));
        if (!movableTargets.contains(target)) {
            throw new IllegalArgumentException("기물이 %s에서 %s로 이동할 수 없습니다.".formatted(source, target));
        }
        piece.moveTo(target);
        movePieceToTarget(source, target);
    }

    private Set<Piece> findAllExistPieceOnBoard() {
        return new HashSet<>(chessboard.values());
    }

    private void validateSourceSquareOccupied(final Square square) {
        if (isBlank(square)) {
            throw new IllegalArgumentException("%s칸에 기물이 존재하지 않습니다.".formatted(square));
        }
    }

    private void movePieceToTarget(final Square source, final Square target) {
        chessboard.put(target, chessboard.get(source));
        chessboard.remove(source);
    }

    public boolean catchKing() {
        long count = chessboard.values().stream()
                .filter(piece -> piece.isTypeOf(PieceType.KING))
                .count();
        return count != INITIAL_KING_COUNT;
    }

    public Score totalScoreOf(Color color) {
        Set<Piece> sameColorPieces = findSameAllyPieces(color);
        Score exceptPawnScore = calculateTotalScoreExceptPawn(sameColorPieces);
        Set<Piece> pawns = filterNotPawns(sameColorPieces);
        return exceptPawnScore.add(pawns.stream()
                .mapToDouble(pawn -> calculatePawnScore(pawn, pawns).getValue())
                .sum());
    }

    private Score calculatePawnScore(Piece pawn, Set<Piece> pawns) {
        if (hasSameFileIn(pawns, pawn.getLocatedFile())) {
            return new Score(0.5);
        }
        return new Score(1.0);
    }

    private Set<Piece> filterNotPawns(Set<Piece> sameColorPieces) {
        return sameColorPieces.stream()
                .filter(piece -> piece.isTypeOf(PieceType.PAWN))
                .collect(Collectors.toSet());
    }

    private Set<Piece> findSameAllyPieces(Color color) {
        return chessboard.values().stream()
                .filter(piece -> piece.isAllyOf(color))
                .collect(Collectors.toSet());
    }

    private Score calculateTotalScoreExceptPawn(Set<Piece> sameColorPieces) {
        return new Score(sameColorPieces.stream()
                .filter(piece -> piece.isNotTypeOf(PieceType.PAWN))
                .mapToDouble(piece -> piece.getScore().getValue())
                .sum());
    }

    private boolean hasSameFileIn(Set<Piece> pawns, File file) {
        long count = pawns.stream()
                .filter(pawn -> pawn.locateSameFile(file))
                .count();
        return count > 1;
    }


    private boolean isBlank(final Square square) {
        return !chessboard.containsKey(square);
    }

    public Map<Square, Piece> getChessboard() {
        return Map.copyOf(chessboard);
    }

    public GameResult findWinner() {
        if (notExistKingOf(WHITE)) {
            return GameResult.BLACK_WIN;
        }

        if (notExistKingOf(BLACK)) {
            return GameResult.WHITE_WIN;
        }
        return findResultByScore();
    }

    private boolean notExistKingOf(Color color) {
        Set<Piece> pieces = findSameAllyPieces(color);
        return pieces.stream()
                .noneMatch(piece -> piece.isTypeOf(PieceType.KING));
    }

    private GameResult findResultByScore() {
        Score whiteScore = totalScoreOf(WHITE);
        Score blackScore = totalScoreOf(BLACK);
        if (whiteScore.isHigherThan(blackScore)) {
            return GameResult.WHITE_WIN;
        }
        if (whiteScore.isLowerThan(blackScore)) {
            return GameResult.BLACK_WIN;
        }
        return GameResult.DRAW;
    }

    public void deleteAllPieces() {
        chessboard.clear();
    }
}
