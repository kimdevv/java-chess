package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.H;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.List;
import java.util.Map;

public class Board {
    private static final MoveStrategy EMPTY_MOVE_STRATEGY = new EmptyMoveStrategy();

    private final Map<Position, Piece> board;
    private final DeadPieces deadPieces;

    public Board(Map<Position, Piece> board) {
        this.board = board;
        this.deadPieces = new DeadPieces();
    }

    public void movePiece(Position source, Position target, Team turn) {
        validatePieceExist(source);
        validateMyTurn(source, turn);

        Piece piece = board.get(source);
        Piece removedPiece = board.get(target);
        Piece movedPiece = movePiece(piece, source, target);
        validateMoveSuccess(piece, movedPiece);
        deadPieces.addPiece(removedPiece);

        removePieceAtSource(source);
        placePieceAtTarget(movedPiece, target);
    }

    public boolean isKingDead() {
        return deadPieces.isContainKing();
    }

    public double calculatePiecesScoreSum(Team team) {
        double piecesScoreSum = 0.0;
        for (int fileIndex = A.getIndex(); fileIndex <= H.getIndex(); fileIndex++) {
            LivePiecesInFile livePiecesInFile = new LivePiecesInFile(getPiecesInFile(fileIndex));
            piecesScoreSum += livePiecesInFile.calculatePieceScoreSum(team);
        }

        return piecesScoreSum;
    }

    private List<Piece> getPiecesInFile(int fileIndex) {
        return board.entrySet().stream()
                .filter(entry -> entry.getKey().isCorrectFile(fileIndex))
                .map(Map.Entry::getValue)
                .toList();
    }

    private void validatePieceExist(Position position) {
        if (!isPieceExistInPosition(position)) {
            throw new IllegalArgumentException("출발지에 체스 말이 없습니다.");
        }
    }

    private void validateMyTurn(Position position, Team turn) {
        if (!isPieceFromSameTeam(position, turn)) {
            throw new IllegalArgumentException("상대 턴입니다.");
        }
    }

    private void validateMoveSuccess(Piece piece, Piece movedPiece) {
        if (piece.equals(movedPiece)) {
            throw new IllegalArgumentException("목적지로 체스 말을 이동시킬 수 없습니다.");
        }
    }

    private void removePieceAtSource(Position source) {
        Piece emptyPiece = new EmptyPiece(new PieceInfo(source, Team.NONE), EMPTY_MOVE_STRATEGY);

        board.put(source, emptyPiece);
    }

    private void placePieceAtTarget(Piece movedPiece, Position target) {
        board.put(target, movedPiece);
    }

    private Piece movePiece(Piece piece, Position source, Position target) {
        return piece.move(target,
                isObstacleInRange(source, target),
                isPieceExistInPosition(target),
                isPieceFromSameTeam(target, piece.getTeam()));
    }

    private boolean isObstacleInRange(Position currentPosition, Position newPosition) {
        List<Position> internalPositions = currentPosition.getInternalPositions(newPosition);

        return internalPositions.stream()
                .map(board::get)
                .anyMatch(piece -> piece.getType() != PieceType.EMPTY);
    }

    private boolean isPieceExistInPosition(Position position) {
        Piece piece = board.get(position);

        return piece.getType() != PieceType.EMPTY;
    }

    private boolean isPieceFromSameTeam(Position position, Team team) {
        Piece piece = board.get(position);

        return piece.isSameTeam(team);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
