package chess.domain.chesspiece;

import chess.domain.chesspiece.movestrategy.BishopMoveStrategy;
import chess.domain.chesspiece.movestrategy.EmptyMoveStrategy;
import chess.domain.chesspiece.movestrategy.KingMoveStrategy;
import chess.domain.chesspiece.movestrategy.KnightMoveStrategy;
import chess.domain.chesspiece.movestrategy.MoveStrategy;
import chess.domain.chesspiece.movestrategy.PawnMoveStrategy;
import chess.domain.chesspiece.movestrategy.QueenMoveStrategy;
import chess.domain.chesspiece.movestrategy.RookMoveStrategy;
import java.util.Arrays;
import java.util.function.Supplier;

public enum ChessPieceType {

    NONE(EmptyMoveStrategy::new, 0),
    KING(KingMoveStrategy::new, 0),
    QUEEN(QueenMoveStrategy::new, 9),
    BISHOP(BishopMoveStrategy::new, 3),
    KNIGHT(KnightMoveStrategy::new, 2.5),
    ROOK(RookMoveStrategy::new, 5),
    PAWN(PawnMoveStrategy::new, 1);

    private final Supplier<MoveStrategy> moveStrategySupplier;
    private final double score;

    ChessPieceType(Supplier<MoveStrategy> moveStrategySupplier, double score) {
        this.moveStrategySupplier = moveStrategySupplier;
        this.score = score;
    }

    public boolean isChessPieceTypeNone() {
        return this.equals(NONE);
    }

    public boolean isChessPieceTypeKing() {
        return this.equals(KING);
    }

    public boolean isChessPieceTypePawn() {
        return this.equals(PAWN);
    }

    public double findChessPieceScore() {
        ChessPieceType chessPieceType = Arrays.stream(values()).filter(type -> type.equals(this))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return chessPieceType.score;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategySupplier.get();
    }

    public static ChessPieceType findChessPieceType(String chessPieceType) {
        return Arrays.stream(values())
                .filter(chessPieceTypeObj -> chessPieceTypeObj.toString().equals(chessPieceType))
                .findFirst().orElseThrow();
    }
}
