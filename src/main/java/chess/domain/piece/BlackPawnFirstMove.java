package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.BlackPawnFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class BlackPawnFirstMove extends Pawn {
    private static final MoveStrategy BLACK_PAWN_FIRST_MOVE_STRATEGY = new BlackPawnFirstMoveStrategy();

    public BlackPawnFirstMove(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new BlackPawnFirstMove(newPieceInfo, BLACK_PAWN_FIRST_MOVE_STRATEGY);
    }

    @Override
    public PieceType getType() {
        return PieceType.BLACK_PAWN_FIRST_MOVE;
    }
}
