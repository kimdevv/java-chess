package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare).getMoveRange();

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
            isFirstMovePosition(chessBoard, startSquare);
        }
    }

    private boolean isFirstMovePosition(ChessBoard chessBoard, Square startSquare) {
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(startSquare);
        if (startSquare.getNumbering().isWhiteCampStartNumbering() && chessPiece.getCamp().isWhiteCamp()) {
            return true;
        }
        return startSquare.getNumbering().isBlackCampStartNumbering() && chessPiece.getCamp().isBlackCamp();
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(startSquare);
        if (chessPiece.getCamp().isBlackCamp()) {
            createMoveRangeByCampBlack(moveRange, chessBoard, startSquare);
        }
        if (chessPiece.getCamp().isWhiteCamp()) {
            createMoveRangeByCampWhite(moveRange, chessBoard, startSquare);
        }
        return moveRange;
    }

    private void createMoveRangeByCampBlack(MoveRange moveRange, ChessBoard chessBoard, Square startSquare) {
        moveRange.addBackward(chessBoard, startSquare);
        if (isFirstMovePosition(chessBoard, startSquare)) {
            moveRange.addBackward(chessBoard, moveRange.firstMoveRange());
        }
        moveRange.addLeftBackwardDiagonal(chessBoard, startSquare);
        moveRange.addRightBackwardDiagonal(chessBoard, startSquare);
    }

    private void createMoveRangeByCampWhite(MoveRange moveRange, ChessBoard chessBoard, Square startSquare) {
        moveRange.addForward(chessBoard, startSquare);
        if (isFirstMovePosition(chessBoard, startSquare)) {
            moveRange.addForward(chessBoard, moveRange.firstMoveRange());
        }
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);
    }
}
