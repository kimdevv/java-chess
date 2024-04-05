package chess.domain.board;

import chess.domain.game.GameCreator;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.route.Route;
import chess.domain.route.Path;
import chess.domain.position.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public static Board from(GameCreator gameCreator) {
        return new Board(gameCreator.createBoard());
    }

    public BoardStatus move(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);
        Route route = Route.create(source, target, this);
        sourcePiece.checkValidMove(source, target, route);

        board.put(source, Empty.instance());
        board.put(target, sourcePiece);

        return BoardStatus.of(sourcePiece, targetPiece);
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }

    public List<Piece> findPieces(Path path) {
        return board.keySet().stream()
                .filter(path::contains)
                .map(board::get)
                .toList();
    }

    public double calculateSameSidePiecesScoreInFile(Side side, File file) {
        List<Piece> pieces = findSameSidePiecesInFile(side, file);

        return pieces.stream()
                .mapToDouble(piece -> piece.score(pieces))
                .sum();
    }

    private List<Piece> findSameSidePiecesInFile(Side side, File file) {
        return board.entrySet().stream()
                .filter(entry -> isSameSidePieceInFile(entry, side, file))
                .map(Entry::getValue)
                .toList();
    }

    private boolean isSameSidePieceInFile(Entry<Position, Piece> entry, Side side, File file) {
        return entry.getKey().isSameFile(file) && entry.getValue().isSameSide(side);
    }

    public List<Piece> pieces() {
        return board.values().stream()
                .toList();
    }
}
