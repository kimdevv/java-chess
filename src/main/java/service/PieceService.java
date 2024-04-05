package service;

import db.dao.PieceDao;
import db.entity.Piece;
import domain.coordinate.Coordinate;
import domain.piece.base.ChessPiece;
import domain.piece.maker.PieceMaker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import state.chessGame.base.ChessGameState;
import view.translator.ColumnSymbol;
import view.translator.PieceTranslator;

public class PieceService {

    private final PieceDao pieceDao;

    public PieceService() {
        this.pieceDao = new PieceDao();
    }

    public void addPieces(Long gameId, Map<Coordinate, ChessPiece> board) throws SQLException {
        List<Piece> pieces = addInitPiece(board, gameId);
        pieceDao.saveAll(gameId, pieces);
    }

    public ChessGameState updatePiece(List<String> inputCommand, ChessGameState chessGameState) throws SQLException {
        Coordinate start = Coordinate.from(inputCommand.get(1));
        Coordinate destination = Coordinate.from(inputCommand.get(2));

        Long gameId = chessGameState.getGameId();
        ChessGameState nextChessGameState = chessGameState.move(start, destination);

        updateSinglePiece(gameId, start, destination);

        return nextChessGameState;
    }

    private void updateSinglePiece(Long gameId, Coordinate start, Coordinate destination) throws SQLException {
        Long startPieceId = pieceDao.findIdByRowAndColumnAndGameId(gameId, start.getRowPosition(),
                start.getColumnPosition());

        Long destinationPieceId = pieceDao.findIdByRowAndColumnAndGameId(gameId,
                destination.getRowPosition(), destination.getColumnPosition());

        pieceDao.updatePieceByRowAndColumn(startPieceId, destination.getRowPosition(),
                destination.getColumnPosition());
        pieceDao.updatePieceByRowAndColumnAndPieceType(destinationPieceId, start.getRowPosition(),
                start.getColumnPosition());
    }

    public void deletePieces(ChessGameState chessGameState) throws SQLException {
        pieceDao.delete(chessGameState.getGameId());
    }

    private List<Piece> addInitPiece(Map<Coordinate, ChessPiece> board, Long gameId) {

        int rowSize = ColumnSymbol.size();
        List<ChessPiece> boardValues = new ArrayList<>(board.values());
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < board.size(); i += rowSize) {
            List<ChessPiece> oneRowPieces = getOneRowPieces(boardValues, i, rowSize);
            setSinglePieces(oneRowPieces, gameId, String.valueOf(8 - i / rowSize), pieces);
        }
        return pieces;
    }

    private void setSinglePieces(List<ChessPiece> oneRowPieces, Long gameId, String row, List<Piece> pieces) {
        for (int column = 0; column < ColumnSymbol.size(); column++) {
            PieceTranslator pieceType = PieceTranslator.from(oneRowPieces.get(column));
            ChessPiece singlePiece = oneRowPieces.get(column);

            pieces.add(Piece.createPiece(gameId, String.valueOf(pieceType), String.valueOf(singlePiece.getColor()),
                    row, String.valueOf(column + 1)));
        }
    }

    private List<ChessPiece> getOneRowPieces(List<ChessPiece> boardValues, int row, int rowSize) {
        return boardValues.subList(row, Math.min(row + rowSize, boardValues.size()));
    }

    public Map<Coordinate, ChessPiece> loadBoard(Long gameId) throws SQLException {
        Map<Coordinate, ChessPiece> board = new LinkedHashMap<>();

        List<Piece> pieces = pieceDao.findAllByGameId(gameId);
        for (Piece piece : pieces) {
            String coordinate = ColumnSymbol.identifierFrom(piece.getColumn());
            ChessPiece foundPiece = PieceMaker.mapPiece(piece);

            board.put(Coordinate.from(coordinate + piece.getRow()), foundPiece);
        }
        return board;
    }
}
