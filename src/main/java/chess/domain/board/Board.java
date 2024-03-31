package chess.domain.board;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.blank.Blank;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.ChessState;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private static final int FILE_MIN = 1;
    private static final int FILE_MAX = 8;
    private static final int KING_TOTAL = 2;

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public ChessState getChessState(Position position) {
        Piece piece = board.get(position);
        return piece.state(this);
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean isAllBlank(Set<Position> positions) {
        return positions.stream()
                .map(board::get)
                .allMatch(Piece::isBlank);
    }

    public boolean hasTwoKing() {
        return board.values().stream()
                .filter(piece -> piece.isTypeOf(PieceType.kings()))
                .count() == KING_TOTAL;
    }

    public Color getRemainKingColor() {
        if (hasTwoKing()) {
            throw new IllegalStateException("아직 게임이 끝나지 않아 남은 킹의 색상을 확인할 수 없습니다.");
        }
        return board.values().stream()
                .filter(piece -> piece.isTypeOf(PieceType.kings()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("왕이 모두 잡혔습니다."))
                .color();
    }

    public void update(Positions positions, Piece target) {
        board.replace(positions.to(), target);
        board.replace(positions.from(), new Blank());
    }

    public List<List<Piece>> findAllPiecesOf(Color color) {
        return IntStream.rangeClosed(FILE_MIN, FILE_MAX)
                .mapToObj(file -> findFilePieces(color, file))
                .toList();
    }

    private List<Piece> findFilePieces(Color color, int file) {
        return IntStream.rangeClosed(FILE_MIN, FILE_MAX)
                .mapToObj(rank -> board.get(new Position(file, rank)))
                .filter(piece -> piece.isSameColor(color))
                .toList();
    }

    public Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
