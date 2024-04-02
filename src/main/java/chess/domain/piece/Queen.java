package chess.domain.piece;

import static chess.domain.pieceinfo.PieceScore.QUEEN_SCORE;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Queen extends ChessPiece {

    public Queen(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new Queen(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE.get();
    }
}
