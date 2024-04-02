package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;

public class BlackPawnNotFirstMove extends Pawn {
    private static final MoveStrategy BLACK_PAWN_NOT_FIRST_MOVE_STRATEGY = new BlackPawnNotFirstMoveStrategy();

    public BlackPawnNotFirstMove(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new BlackPawnNotFirstMove(newPieceInfo, BLACK_PAWN_NOT_FIRST_MOVE_STRATEGY);
    }

    @Override
    public PieceType getType() {
        return PieceType.BLACK_PAWN_NOT_FIRST_MOVE;
    }
}
