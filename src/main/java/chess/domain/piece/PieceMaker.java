package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.BlackPawnFirstMoveStrategy;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;
import java.util.function.Function;

public enum PieceMaker {
    ROOK(pieceInfo -> new Rook(pieceInfo, new RookMoveStrategy())),
    KNIGHT(pieceInfo -> new Knight(pieceInfo, new KnightMoveStrategy())),
    BISHOP(pieceInfo -> new Bishop(pieceInfo, new BishopMoveStrategy())),
    QUEEN(pieceInfo -> new Queen(pieceInfo, new QueenMoveStrategy())),
    KING(pieceInfo -> new King(pieceInfo, new KingMoveStrategy())),
    BLACK_PAWN_FIRST_MOVE(pieceInfo -> new BlackPawnFirstMove(pieceInfo, new BlackPawnFirstMoveStrategy())),
    BLACK_PAWN_NOT_FIRST_MOVE(pieceInfo -> new BlackPawnNotFirstMove(pieceInfo, new BlackPawnNotFirstMoveStrategy())),
    WHITE_PAWN_FIRST_MOVE(pieceInfo -> new WhitePawnFirstMove(pieceInfo, new WhitePawnFirstMoveStrategy())),
    WHITE_PAWN_NOT_FIRST_MOVE(pieceInfo -> new WhitePawnNotFirstMove(pieceInfo, new WhitePawnNotFirstMoveStrategy()));

    private final Function<PieceInfo, Piece> pieceConstructor;

    PieceMaker(Function<PieceInfo, Piece> pieceConstructor) {
        this.pieceConstructor = pieceConstructor;
    }

    public Piece createPiece(PieceInfo pieceInfo) {
        return pieceConstructor.apply(pieceInfo);
    }
}
