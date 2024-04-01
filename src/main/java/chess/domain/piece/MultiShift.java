package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiShift extends Piece {
    protected MultiShift(Color color, PieceType pieceType, Square square) {
        super(color, pieceType, square);
    }

    @Override
    public Set<Square> findLegalMoves(Set<Piece> entirePieces) {
        Set<Square> movableSquares = new HashSet<>();
        for (Movement movement : movements()) {
            addToMovableSquares(entirePieces, movement, movableSquares);
        }
        return movableSquares;
    }

    private void addToMovableSquares(Set<Piece> entirePieces, Movement movement, Set<Square> movableSquares) {
        Square currentSquare = currentSquare();
        while (currentSquare.canMove(movement)) {
            currentSquare = currentSquare.move(movement);
            if (isOccupied(entirePieces, currentSquare)) {
                Piece piece = getPiece(entirePieces, currentSquare);
                addToMovableSquareIfEnemy(movableSquares, piece);
                break;
            }
            movableSquares.add(currentSquare);
        }
    }

    private Piece getPiece(Set<Piece> entirePieces, Square existPieceSquare) {
        return entirePieces.stream()
                .filter(piece -> piece.currentSquare() == existPieceSquare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("%s 칸에 기물이 없습니다.".formatted(existPieceSquare)));
    }

    private boolean isOccupied(Set<Piece> entirePieces, Square currentSquare) {
        return entirePieces.stream()
                .anyMatch(piece -> piece.currentSquare() == currentSquare);
    }

    private void addToMovableSquareIfEnemy(Set<Square> movableSquare, Piece piece) {
        if (isEnemyOf(piece)) {
            movableSquare.add(piece.currentSquare());
        }
    }
}
