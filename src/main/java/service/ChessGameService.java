package service;

import db.dao.BoardDao;
import db.dao.GameDao;
import domain.chessboard.ChessBoard;
import domain.chessboard.MovingResult;
import domain.coordinate.Coordinate;
import domain.coordinate.CoordinateMapper;
import domain.game.ChessGame;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.type.ChessPieceMapper;
import domain.piece.type.PieceType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import service.dto.CoordinateDto;

public class ChessGameService {

    private static final ChessGameService instance = new ChessGameService();
    private final BoardDao boardDao;
    private final GameDao gameDao;

    private ChessGameService() {
        boardDao = BoardDao.getInstance();
        gameDao = GameDao.getInstance();
    }

    public static ChessGameService getInstance() {
        return instance;
    }

    public ChessGame createInitialGame() {
        deleteGame();
        ChessGame chessGame = new ChessGame();
        List<CoordinateDto> boardDto = createBoardDto(chessGame.getCurrentBoard());
        boardDao.createBoard(boardDto);
        gameDao.createColor();

        return new ChessGame();
    }

    public ChessGame loadGame() {
        String movingColor = gameDao.loadColor();
        List<CoordinateDto> coordinateDtos = boardDao.loadBoard();
        ChessBoard chessBoard = loadBoard(coordinateDtos);

        return new ChessGame(chessBoard, Color.valueOf(movingColor));
    }

    public boolean move(ChessGame chessGame, String startCoordinate, String destinationCoordinate) {
        Coordinate start = CoordinateMapper.getCoordinate(startCoordinate);
        Coordinate destination = CoordinateMapper.getCoordinate(destinationCoordinate);
        MovingResult movingResult = chessGame.startTurn(start, destination);
        boolean isKingRemoved = movingResult.isKingRemoved();
        if (isKingRemoved) {
            deleteGame();
        }
        if (!isKingRemoved) {
            updateGame(start, destination, movingResult, chessGame.getMovablePieceColor());
        }
        return isKingRemoved;
    }

    public void updateGame(Coordinate start, Coordinate destination, MovingResult movingResult, Color movableColor) {
        boardDao.updateBoard(
                CoordinateDto.from(start, Blank.getInstance()),
                CoordinateDto.from(destination, movingResult.getMovingPiece())
        );
        gameDao.updateColor(movableColor.name());
    }

    public void deleteGame() {
        gameDao.delete();
        boardDao.delete();
    }

    public List<List<ChessPiece>> getPiecesInBoard(ChessGame chessGame) {
        Map<Coordinate, ChessPiece> board = chessGame.getCurrentBoard();
        List<List<ChessPiece>> result = new ArrayList<>();
        for (int row = 0; row < ChessBoard.SIZE; row++) {
            result.add(getPiecesInRow(board, row));
        }
        return result;
    }

    private List<ChessPiece> getPiecesInRow(Map<Coordinate, ChessPiece> board, int row) {
        List<ChessPiece> result = new ArrayList<>();
        for (int column = 0; column < ChessBoard.SIZE; column++) {
            result.add(board.get(new Coordinate(row, column)));
        }
        return result;
    }

    private ChessBoard loadBoard(List<CoordinateDto> coordinateDtos) {
        return new ChessBoard(coordinateDtos.stream()
                .collect(Collectors.toMap(this::mapDtoToCoordinate, this::mapDtoToPiece))
        );
    }

    private Coordinate mapDtoToCoordinate(CoordinateDto coordinateDto) {
        return CoordinateMapper.getCoordinate(coordinateDto.coordinate());
    }

    private ChessPiece mapDtoToPiece(CoordinateDto coordinateDto) {
        PieceType pieceType = PieceType.valueOf(coordinateDto.pieceType());
        Color color = Color.valueOf(coordinateDto.pieceColor());

        return ChessPieceMapper.getInstance(pieceType, color);
    }

    private List<CoordinateDto> createBoardDto(Map<Coordinate, ChessPiece> board) {
        return board.entrySet().stream()
                .map(entry -> CoordinateDto.from(entry.getKey(), entry.getValue()))
                .toList();
    }
}
