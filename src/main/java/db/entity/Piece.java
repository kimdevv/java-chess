package db.entity;

public class Piece {
    private final Long gameId;
    private final String pieceType;
    private final String colorType;
    private final String row;
    private final String column;

    private Piece(Long gameId, String pieceType, String colorType, String row, String column) {
        this.gameId = gameId;
        this.pieceType = pieceType;
        this.colorType = colorType;
        this.row = row;
        this.column = column;
    }

    public static Piece createPiece(Long gameId, String pieceType, String colorType, String row, String column) {
        return new Piece(gameId, pieceType, colorType, row, column);
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getColorType() {
        return colorType;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }
}
