package controller.dto;

import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;
import java.util.List;
import view.util.ChessPieceSymbol;

public record ChessBoardStatusDto(List<List<String>> boardStatus) {

    public static ChessBoardStatusDto from(List<List<ChessPiece>> piecesInBoard) {
        return new ChessBoardStatusDto(piecesInBoard.stream()
                .map(piecesInRow -> piecesInRow.stream().map(ChessBoardStatusDto::mapPieceToSymbol).toList())
                .toList()
        );
    }

    private static String mapPieceToSymbol(ChessPiece chessPiece) {
        PieceType pieceType = chessPiece.getType();
        String symbol = ChessPieceSymbol.getSymbol(pieceType);

        if (chessPiece.hasSameColor(Color.WHITE)) {
            return symbol;
        }
        return symbol.toUpperCase();
    }
}
