package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.ChessRoomDao;
import chess.domain.dao.PieceDao;
import chess.domain.dto.PieceDto;
import chess.domain.initializer.BoardInitializer;
import chess.domain.initializer.ChessRoomInitializer;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMaker;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    private static final int BOARD_SIZE = 8;
    private static final MoveStrategy EMPTY_MOVE_STRATEGY = new EmptyMoveStrategy();

    private final Long chess_room_id;
    private final PieceDao pieceDao;
    private final ChessRoomDao chessRoomDao;

    public ChessService(Long chessRoomId, PieceDao pieceDao, ChessRoomDao chessRoomDao) {
        this.chess_room_id = chessRoomId;
        this.pieceDao = pieceDao;
        this.chessRoomDao = chessRoomDao;
    }

    public void initializeChess() {
        pieceDao.deleteAllByChessRoomId(chess_room_id);
        chessRoomDao.deleteChessRoomById(chess_room_id);

        BoardInitializer.initialize(pieceDao, chess_room_id);
        ChessRoomInitializer.initialize(chessRoomDao, chess_room_id);
    }

    public Board loadPieces() {
        List<PieceDto> pieceDtos = pieceDao.findAllByChessRoomId(chess_room_id);

        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDtos) {
            Piece piece = changePieceDtoToPiece(pieceDto);
            Position position = piece.getPosition();

            pieces.put(position, piece);
        }
        fillEmptyPieces(pieces);

        return new Board(pieces);
    }

    public Team loadTurn() {
        String turn = chessRoomDao.findTurnById(chess_room_id);
        return Team.valueOf(turn);
    }

    private void fillEmptyPieces(Map<Position, Piece> pieces) {
        List<Position> positions = createAllPiecePositions();
        positions.stream()
                .filter(position -> !pieces.containsKey(position))
                .forEach(position -> pieces.put(position,
                        new EmptyPiece(new PieceInfo(position, Team.NONE), EMPTY_MOVE_STRATEGY)));
    }

    private static List<Position> createAllPiecePositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            positions.add(Position.of("" + (char) (i / BOARD_SIZE + 'a') + (char) (i % BOARD_SIZE + '1')));
        }
        return positions;
    }

    public void savePieces(Board board) {
        Map<Position, Piece> pieces = board.getBoard();

        pieces.values().stream()
                .filter(piece -> piece.getType() != PieceType.EMPTY)
                .forEach(piece -> pieceDao.addPieceByChessRoomId(changePieceToPieceDto(piece), chess_room_id));
    }

    public void deletePieces() {
        pieceDao.deleteAllByChessRoomId(chess_room_id);
    }

    public void saveTurn(Team turn) {
        chessRoomDao.updateTurnById(turn.getRawTeam(), chess_room_id);
    }
    
    private PieceDto changePieceToPieceDto(Piece piece) {
        Position position = piece.getPosition();
        PieceType pieceType = piece.getType();
        Team team = piece.getTeam();

        return new PieceDto(
                createRawPosition(position),
                pieceType.getRawPieceType(),
                team.getRawTeam()
        );
    }

    private Piece changePieceDtoToPiece(PieceDto pieceDto) {
        Position position = Position.of(pieceDto.position());
        Team team = Team.valueOf(pieceDto.team());
        PieceInfo pieceInfo = new PieceInfo(position, team);

        PieceMaker pieceMaker = PieceMaker.valueOf(pieceDto.type());
        return pieceMaker.createPiece(pieceInfo);
    }

    private String createRawPosition(Position position) {
        return position.getRawFile() + position.getRawRank();
    }
}
