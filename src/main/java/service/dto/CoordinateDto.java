package service.dto;

import domain.coordinate.Coordinate;
import domain.coordinate.CoordinateMapper;
import domain.piece.base.ChessPiece;

public record CoordinateDto(String coordinate, String pieceType, String pieceColor) {

    public static CoordinateDto from(Coordinate coordinate, ChessPiece chessPiece) {
        String coordinateName = CoordinateMapper.getCoordinateName(coordinate);
        String pieceType = chessPiece.getType().name();
        String pieceColor = chessPiece.getColor().name();

        return new CoordinateDto(coordinateName, pieceType, pieceColor);
    }
}
