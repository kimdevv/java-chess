package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import java.util.Set;

public final class PawnChessState extends ChessState {

    public PawnChessState(Board board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Positions positions) {
        Pawn currentPiece = (Pawn) board.getPiece(positions.from());
        checkTurnOf(currentPiece, turnColor);
        Piece destinationPiece = board.getPiece(positions.to());
        validateMovable(positions, currentPiece);
        validateWithCapture(positions, currentPiece, destinationPiece);
        board.update(positions, currentPiece.update());
    }

    private void validateWithCapture(Positions positions, Pawn currentPiece, Piece destinationPiece) {
        if (!currentPiece.isCaptureMove(positions) && !destinationPiece.isBlank()) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
        if (currentPiece.isCaptureMove(positions) && !currentPiece.isOppositeColor(destinationPiece)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }

    private void validateMovable(Positions positions, Pawn currentPiece) {
        Set<Position> pathToDestination = currentPiece.findPath(positions);
        if (isNotAllBlankPath(pathToDestination)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치입니다.");
        }
    }
}
