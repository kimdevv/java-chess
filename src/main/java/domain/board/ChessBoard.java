package domain.board;

import domain.Side;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    public static final int ALL_KING_ALIVE = 2;

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public Piece findPiece(Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
        return board.get(position);
    }

    private boolean isEmpty(Position position) {
        return !board.containsKey(position);
    }

    public void move(Position current, Position target) {
        Piece piece = findMovablePiece(current, target);
        board.remove(current);
        board.put(target, piece);
    }

    private Piece findMovablePiece(Position current, Position target) {
        Map<Position, Piece> piecesOnPath = findPiecesOnPath(current, target);

        Piece piece = board.get(current);
        piece.checkMovable(current, target, piecesOnPath);
        return piece;
    }

    private Map<Position, Piece> findPiecesOnPath(Position current, Position target) {
        Path path = Path.createOf(current, target);
        return path.findPieces(board);
    }

    public boolean isAllKingAlive() {
        long kingCount = board.values().stream()
                .filter(Piece::isKing)
                .count();

        return kingCount == ALL_KING_ALIVE;
    }

    public List<Piece> collectMajorPieces(Side side) {
        return board.values().stream()
                .filter(piece -> piece.isSameSide(side))
                .filter(Piece::isMajorPiece)
                .toList();
    }

    public Map<Position, Piece> collectPawns(Side side) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameSide(side))
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
