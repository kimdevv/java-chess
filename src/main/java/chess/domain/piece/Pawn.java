package chess.domain.piece;

import static chess.domain.pieceinfo.PieceScore.PAWN_SCORE;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class Pawn extends ChessPiece {

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public abstract ChessPiece createNewPiece(PieceInfo newPieceInfo);

    @Override
    public abstract PieceType getType();

    @Override
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        int diffX = Math.abs(currentPosition.getFileIndex() - newPosition.getFileIndex());

        boolean isInvalidVerticalMove = (diffX == 0) && isOtherPieceExist;
        boolean isInvalidDiagonalMove = (diffX == 1) && (!isOtherPieceExist || isSameTeamExist);

        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return true;
        }
        return isDisturbed || isInvalidVerticalMove || isInvalidDiagonalMove;
    }

    @Override
    public double getScore() {
        return PAWN_SCORE.get();
    }
}
