package chess.domain.chesspiece;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.movestrategy.MoveStrategy;

public class ChessPieceProperty {

    private final ChessPieceType chessPieceType;
    private final MoveStrategy moveStrategy;

    public ChessPieceProperty(ChessPieceType chessPieceType, MoveStrategy moveStrategy) {
        this.chessPieceType = chessPieceType;
        this.moveStrategy = moveStrategy;
    }

    public void executeMoveStrategy(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        validateEmptyChessPiece();
        moveStrategy.move(chessBoard, startSquare, targetSquare);
    }

    private void validateEmptyChessPiece() {
        if (chessPieceType == ChessPieceType.NONE) {
            throw new IllegalArgumentException("[ERROR] 이동할 체스말이 없습니다.");
        }
    }

    public ChessPieceType getChessPieceType() {
        return chessPieceType;
    }
}
