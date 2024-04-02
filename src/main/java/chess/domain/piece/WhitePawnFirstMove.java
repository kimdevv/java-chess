package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;

public class WhitePawnFirstMove extends Pawn {
    private static final MoveStrategy WHITE_PAWN_FIRST_MOVE_STRATEGY = new WhitePawnFirstMoveStrategy();

    public WhitePawnFirstMove(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new WhitePawnFirstMove(newPieceInfo, WHITE_PAWN_FIRST_MOVE_STRATEGY);
    }

    @Override
    public PieceType getType() {
        return PieceType.WHITE_PAWN_FIRST_MOVE;
    }
}
