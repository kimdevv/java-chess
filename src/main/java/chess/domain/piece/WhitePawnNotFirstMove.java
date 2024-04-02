package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;

public class WhitePawnNotFirstMove extends Pawn {
    private static final MoveStrategy WHITE_PAWN_NOT_FIRST_MOVE_STRATEGY = new WhitePawnNotFirstMoveStrategy();

    public WhitePawnNotFirstMove(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new WhitePawnNotFirstMove(newPieceInfo, WHITE_PAWN_NOT_FIRST_MOVE_STRATEGY);
    }

    @Override
    public PieceType getType() {
        return PieceType.WHITE_PAWN_NOT_FIRST_MOVE;
    }
}
